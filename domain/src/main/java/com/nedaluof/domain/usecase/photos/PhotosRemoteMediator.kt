package com.nedaluof.domain.usecase.photos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nedaluof.data.model.db.photo.PhotoEntity
import com.nedaluof.data.model.db.photo.PhotoPagingKey
import com.nedaluof.data.repository.popularphotos.PopularPhotosRepository
import com.nedaluof.domain.utils.exceptionMessage
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created By NedaluOf - 7/22/2023.
 */
@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
  private val repository: PopularPhotosRepository
) : RemoteMediator<Int, PhotoEntity>() {

  override suspend fun initialize(): InitializeAction {
    val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    return if (System.currentTimeMillis() - (repository.getLastCreationTimeOfPagingKey()
        ?: 0) < cacheTimeout
    ) {
      InitializeAction.SKIP_INITIAL_REFRESH
    } else {
      InitializeAction.LAUNCH_INITIAL_REFRESH
    }
  }

  private suspend fun getPagingKeyClosestToCurrentPosition(state: PagingState<Int, PhotoEntity>): PhotoPagingKey? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.let { entity ->
        repository.getPhotoPagingKeyById(entity.photoData.id)
      }
    }
  }

  private suspend fun getPagingKeyForFirstItem(state: PagingState<Int, PhotoEntity>): PhotoPagingKey? {
    return state.pages.firstOrNull {
      it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let { entity ->
      repository.getPhotoPagingKeyById(entity.photoData.id)
    }
  }

  private suspend fun getPagingKeyForLastItem(state: PagingState<Int, PhotoEntity>): PhotoPagingKey? {
    return state.pages.lastOrNull {
      it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let { entity ->
      repository.getPhotoPagingKeyById(entity.photoData.id)
    }
  }

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, PhotoEntity>
  ): MediatorResult {
    val currentPage: Int = when (loadType) {
      LoadType.REFRESH -> {
        val pagingKey = getPagingKeyClosestToCurrentPosition(state)
        pagingKey?.nextKey?.minus(1) ?: 1
      }

      LoadType.PREPEND -> {
        val pagingKey = getPagingKeyForFirstItem(state)
        pagingKey?.prevKey
          ?: return MediatorResult.Success(endOfPaginationReached = pagingKey != null)
      }

      LoadType.APPEND -> {
        val pagingKey = getPagingKeyForLastItem(state)
        pagingKey?.nextKey
          ?: return MediatorResult.Success(endOfPaginationReached = pagingKey != null)
      }
    }

    try {
      val apiResponse = repository.loadPhotoList(currentPage)
      val photosList = apiResponse.body()?.hits ?: emptyList()
      val endOfPaginationReached = photosList.isEmpty()

      /**
       * to avoid rate limit error from the api owners
       * which is 100 request per minute
       * */
      delay(1000)

      repository.transactionBlock {
        if (loadType == LoadType.REFRESH) {
          //clear tables if LoadType is REFRESH
          repository.clearPagingKeysTable()
          repository.clearPhotosTable()
        }
        //calculate new keys
        val prevKey = if (currentPage > 1) currentPage - 1 else null
        val nextKey = if (endOfPaginationReached) null else currentPage + 1
        Timber.e("new page -> $nextKey")
        val pagingKeys = photosList.map {
          PhotoPagingKey(
            photoId = it.id,
            prevKey = prevKey,
            currentPage = currentPage,
            nextKey = nextKey
          )
        }

        repository.insertPhotoPagingKeys(pagingKeys)
        repository.insertPhotoEntitiesList(photosList.map { PhotoEntity(photoData = it) })
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (exception: Exception) {
      Timber.e(exception.exceptionMessage())
      return MediatorResult.Error(exception)
    }
  }
}
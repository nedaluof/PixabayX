package com.nedaluof.domain.usecase.photos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.repository.popularphotos.PopularPhotosRepository
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/22/2023.
 */
class PhotosUseCaseImpl @Inject constructor(
  private val repository: PopularPhotosRepository,
  private val mapper: Mapper<PhotoData, PhotoModel>
) : PhotosUseCase {

  @OptIn(ExperimentalPagingApi::class)
  override fun loadPhotosList(): Flow<PagingData<PhotoModel>> {
    return Pager(
      config = PagingConfig(
        pageSize = 25,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { repository.loadCachedPhotoList() },
      remoteMediator = PhotosRemoteMediator(repository)
    ).flow.map {
      it.map { photoEntity ->
        mapper.fromInput(photoEntity.photoData)
      }
    }
  }
}
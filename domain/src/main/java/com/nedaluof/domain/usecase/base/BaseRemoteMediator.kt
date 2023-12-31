package com.nedaluof.domain.usecase.base

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nedaluof.data.model.db.common.PagingKey
import com.nedaluof.domain.utils.exceptionMessage
import com.nedaluof.domain.utils.log
import kotlinx.coroutines.delay
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created By NedaluOf - 7/22/2023.
 * Updated By NedaluOf - 8/14/2023.
 */
@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator<ENTITY : Any, PAGING_KEY : PagingKey, RESPONSE, RESPONSE_DATA> :
  RemoteMediator<Int, ENTITY>() {

  /**
   * request last category photo paging key
   * creation time from the client
   * @return [Long]
   * */
  abstract suspend fun getLastCreationTimeOfPagingKey(): Long?

  /**
   * request paging key by [ENTITY] from the client
   * @return [PAGING_KEY]?
   * */
  abstract suspend fun getPagingKeyByEntity(
    entity: ENTITY
  ): PAGING_KEY?

  /**
   * request Api service with [page] from the client
   * @return Response<[RESPONSE]>
   * */
  abstract suspend fun requestApi(
    page : Int
  ): Response<RESPONSE>

  /**
   * request appropriate data from the client
   * by the received response
   * @return List<[RESPONSE_DATA]>
   * */
  abstract suspend fun getDataListFromResponse(
    response: Response<RESPONSE>
  ): List<RESPONSE_DATA>?

  /**
   * request database transaction block from the client
   * */
  abstract suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T?

  /**
   * ask the client to clear tables when
   * LoadType is REFRESH
   * */
  abstract suspend fun clearDataAndPagingKeysTables()

  /**
   * ask the client to insert new data with
   * pagination keys into corresponding
   * database tables
   * */
  abstract suspend fun insertNewData(
    data: List<RESPONSE_DATA>,
    nextKey: Int?,
    prevKey: Int?,
    currentPage: Int
  )

  override suspend fun initialize(): InitializeAction {
    val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    return if (System.currentTimeMillis() - (getLastCreationTimeOfPagingKey() ?: 0) < cacheTimeout
    ) {
      InitializeAction.SKIP_INITIAL_REFRESH
    } else {
      InitializeAction.LAUNCH_INITIAL_REFRESH
    }
  }

  private suspend fun getPagingKeyClosestToCurrentPosition(state: PagingState<Int, ENTITY>): PAGING_KEY? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.let { entity ->
        getPagingKeyByEntity(entity)
      }
    }
  }

  private suspend fun getPagingKeyForFirstItem(state: PagingState<Int, ENTITY>): PAGING_KEY? {
    return state.pages.firstOrNull {
      it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let { entity ->
      getPagingKeyByEntity(entity)
    }
  }

  private suspend fun getPagingKeyForLastItem(state: PagingState<Int, ENTITY>): PAGING_KEY? {
    return state.pages.lastOrNull {
      it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let { entity ->
      getPagingKeyByEntity(entity)
    }
  }

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, ENTITY>
  ): MediatorResult {
    val currentPage: Int = when (loadType) {
      LoadType.REFRESH -> {
        val pagingKey = getPagingKeyClosestToCurrentPosition(state)
        pagingKey?.nextKey?.minus(PAGE) ?: PAGE
      }

      LoadType.PREPEND -> {
        val pagingKey = getPagingKeyForFirstItem(state)
        pagingKey?.prevKey
          ?: return MediatorResult.Success(endOfPaginationReached = pagingKey != null)
      }

      LoadType.APPEND -> {
        val pagingKey = getPagingKeyForLastItem(state)
        pagingKey?.nextKey ?: PAGE
      }
    }

    try {
      val apiResponse = getDataListFromResponse(requestApi(currentPage))
      val dataList = apiResponse ?: emptyList()
      val endOfPaginationReached = dataList.isEmpty()

      /**
       * to avoid rate limit error from the api owners
       * which is 100 request per minute
       * */
      delay(1000)

      transactionBlock {
        if (loadType == LoadType.REFRESH) {
          //clear tables if LoadType is REFRESH
          clearDataAndPagingKeysTables()
        }

        //calculate new keys
        val prevKey = if (currentPage > PAGE) currentPage - PAGE else null
        val nextKey = if (endOfPaginationReached) null else currentPage + PAGE
        Timber.e("new page -> $nextKey")
        insertNewData(dataList, nextKey, prevKey, currentPage)
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (exception: Exception) {
      log(exception.exceptionMessage())
      return MediatorResult.Error(exception)
    }
  }

  companion object {
    private const val PAGE = 1
  }
}
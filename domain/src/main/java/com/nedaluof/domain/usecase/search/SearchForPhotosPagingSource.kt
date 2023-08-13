package com.nedaluof.domain.usecase.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.repository.search.SearchForPhotosRepository
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.domain.utils.exceptionMessage
import timber.log.Timber

/**
 * Created By NedaluOf - 7/31/2023.
 */
class SearchForPhotosPagingSource(
  private val searchQuery: String,
  private val repository: SearchForPhotosRepository,
  private val mapper: Mapper<PhotoData, PhotoModel>
) : PagingSource<Int, PhotoModel>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
    val startIndex = 1
    val page = params.key ?: startIndex
    return try {
      val response = repository.searchFoPhotos(searchQuery, page)
      val photos = response.body()?.hits ?: emptyList()
      val photosList = photos.map { mapper.fromInput(it) }
      LoadResult.Page(
        data = photosList,
        prevKey = if (page == startIndex) null else page - 1,
        nextKey = if (photosList.isEmpty()) null else page + 1
      )
    } catch (exception: Exception) {
      Timber.e(exception.exceptionMessage())
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, PhotoModel>) = null
}
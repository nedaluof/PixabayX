package com.nedaluof.domain.usecase.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.repository.search.SearchForPhotosRepository
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/31/2023.
 */
class SearchForPhotosUseCaseImpl @Inject constructor(
  private val repository: SearchForPhotosRepository,
  private val mapper: Mapper<PhotoData, PhotoModel>
) : SearchForPhotosUseCase {
  /**
   * search for photos by @param [query]
   * @return flow contain PagingData of [PhotoModel]
   * */
  override fun searchForPhotosByQuery(
    query: String
  ): Flow<PagingData<PhotoModel>> {
    return Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = {
        SearchForPhotosPagingSource(
          query,
          repository, mapper
        )
      }
    ).flow
  }
}
package com.nedaluof.domain.usecase.search

import androidx.paging.PagingData
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/31/2023.
 */
interface SearchForPhotosUseCase {
  /**
   * search for photos by @param [query]
   * @return flow contain PagingData of [PhotoModel]
   * */
  fun searchForPhotosByQuery(
    query: String
  ): Flow<PagingData<PhotoModel>>
}
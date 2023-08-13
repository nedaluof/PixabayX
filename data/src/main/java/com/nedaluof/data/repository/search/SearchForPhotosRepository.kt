package com.nedaluof.data.repository.search

import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import retrofit2.Response

/**
 * Created By NedaluOf - 7/31/2023.
 */
interface SearchForPhotosRepository {
  /**
   * search for photos paginated using @param [query]
   * @return [Response<SearchForPhotosResponse>]
   * */
  suspend fun searchFoPhotos(
    query: String,
    page: Int
  ): Response<LoadPhotosResponse>
}
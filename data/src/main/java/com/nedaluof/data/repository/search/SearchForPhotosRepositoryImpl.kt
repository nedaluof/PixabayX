package com.nedaluof.data.repository.search

import com.nedaluof.data.BuildConfig
import com.nedaluof.data.datasource.remote.api.PixabayXApiServices
import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/31/2023.
 */
class SearchForPhotosRepositoryImpl @Inject constructor(
  private val api: PixabayXApiServices
) : SearchForPhotosRepository {
  /**
   * search for photos paginated using @param [query]
   * @return [Response<SearchForPhotosResponse>]
   * */
  override suspend fun searchFoPhotos(
    query: String,
    page: Int
  ): Response<LoadPhotosResponse> = api.searchForPhotos(BuildConfig.API_KEY, query, page)
}
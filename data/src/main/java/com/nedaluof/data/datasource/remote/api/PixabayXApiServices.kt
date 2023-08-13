package com.nedaluof.data.datasource.remote.api

import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By NedaluOf - 7/22/2023.
 */
interface PixabayXApiServices {

  @GET("?safesearch=true&pretty=true")
  suspend fun loadPopularPhotos(
    @Query("key") apiKey: String = "",
    @Query("page") page: Int,
    @Query("order") order: String = "popular"
  ): Response<LoadPhotosResponse>

  @GET("?safesearch=true&pretty=true")
  suspend fun loadPhotosByCategory(
    @Query("key") apiKey: String = "",
    @Query("category") category: String,
    @Query("q") query: String,
    @Query("page") page: Int
  ): Response<LoadPhotosResponse>

  @GET("?safesearch=true&pretty=true")
  suspend fun searchForPhotos(
    @Query("key") apiKey: String = "",
    @Query("q") query: String,
    @Query("page") page: Int = 1
  ): Response<LoadPhotosResponse>
}
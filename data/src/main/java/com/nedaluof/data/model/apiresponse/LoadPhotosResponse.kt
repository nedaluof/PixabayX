package com.nedaluof.data.model.apiresponse

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created By NedaluOf - 7/22/2023.
 */
@JsonClass(generateAdapter = true)
data class LoadPhotosResponse(
  val total : Long,
  val totalHits : Long,
  val hits : List<PhotoData>
)

@JsonClass(generateAdapter = true)
data class PhotoData(
  val id: Long,
  val pageURL: String?,
  val type: String?,
  val tags: String?,
  val previewURL: String?,
  val previewWidth: Long?,
  val previewHeight: Long?,
  val webformatURL: String?,
  val webformatWidth: Long?,
  val webformatHeight: Long?,
  val largeImageURL: String?,
  val imageWidth: Long?,
  val imageHeight: Long?,
  val imageSize: Long?,
  val views: Long?,
  val downloads: Long?,
  val collections: Long?,
  val likes: Long?,
  val comments: Long?,
  @Json(name = "user_id")
  val userId: Long?,
  val user: String?,
  val userImageURL: String?
)
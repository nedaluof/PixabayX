package com.nedaluof.domain.model.photos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created By NedaluOf - 7/26/2023.
 */
@Parcelize
data class PhotoModel(
  var id: String = "",
  var width: Long = 0,
  var height: Long = 0,
  var likes: Long = 0,
  var imageSize: Long = 0,
  var views: Long = 0,
  var downloads: Long = 0,
  var owner: PhotoOwner = PhotoOwner(),
  var url: String = "",
  var downloadLink: String = "",
) : Parcelable

@Parcelize
data class PhotoOwner(
  var id: Long = 0,
  var name: String = "",
  var profileImage: String = "",
) : Parcelable
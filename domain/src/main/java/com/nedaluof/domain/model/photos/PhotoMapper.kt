package com.nedaluof.domain.model.photos

import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.domain.model.common.Mapper
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/26/2023.
 */
class PhotoMapper @Inject constructor() : Mapper<PhotoData, PhotoModel> {

  override fun fromInput(input: PhotoData) = with(PhotoModel()) {
    id = input.id.toString()
    width = input.previewWidth ?: 0
    height = input.previewHeight ?: 0
    likes = input.likes ?: 0
    imageSize = input.imageSize ?: 0
    views = input.views ?: 0
    downloads = input.downloads ?: 0
    owner = PhotoOwner(
      input.userId ?: 0,
      input.user ?: "",
      input.userImageURL ?: ""
    )
    url = input.largeImageURL ?: ""
    downloadLink = input.previewURL ?: ""
    this
  }
}
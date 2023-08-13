package com.nedaluof.domain.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created By NedaluOf - 7/28/2023.
 */
@Parcelize
data class CategoryModel(
  var id: Int = 0,
  var name: String = "" ,
  var coverImage: String = "https://pixabay.com/get/g6132ccff84f45e3006df9fedfb55084c1be76bcc013e7527c667ebef7d17ee15ee4e15c1c27578b9e471e9d07cc5712c0d9654add3a9cd973b15ae488840159b_1280.jpg"
) : Parcelable
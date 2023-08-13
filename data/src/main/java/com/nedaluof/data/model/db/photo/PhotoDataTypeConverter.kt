package com.nedaluof.data.model.db.photo

import androidx.room.TypeConverter
import com.nedaluof.data.model.apiresponse.PhotoData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Created By NedaluOf - 7/22/2023.
 */
class PhotoDataTypeConverter {
  private val moshi: Moshi = Moshi.Builder().build()

  @TypeConverter
  fun toString(data: PhotoData): String {
    val adapter: JsonAdapter<PhotoData> = moshi.adapter(PhotoData::class.java)
    return adapter.toJson(data)
  }

  @TypeConverter
  fun fromString(data: String): PhotoData? {
    val adapter: JsonAdapter<PhotoData> = moshi.adapter(PhotoData::class.java)
    return adapter.fromJson(data)
  }
}
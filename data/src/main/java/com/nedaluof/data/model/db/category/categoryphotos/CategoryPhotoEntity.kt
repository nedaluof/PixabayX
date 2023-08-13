package com.nedaluof.data.model.db.category.categoryphotos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nedaluof.data.model.apiresponse.PhotoData
import kotlin.random.Random

/**
 * Created By NedaluOf - 7/29/2023.
 */
@Entity(tableName = "category_photos_table")
data class CategoryPhotoEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  @ColumnInfo("time_stamp")
  val timeStamp: Long = System.currentTimeMillis() + Random(2017).nextInt(),
  @ColumnInfo("category_id")
  var categoryId: Int,
  @ColumnInfo("photo_data")
  var photoData: PhotoData
)
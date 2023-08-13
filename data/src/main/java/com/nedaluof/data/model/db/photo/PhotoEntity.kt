package com.nedaluof.data.model.db.photo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nedaluof.data.model.apiresponse.PhotoData
import kotlin.random.Random

/**
 * Created By NedaluOf - 7/22/2023.
 */
@Entity(tableName = "photos_table")
data class PhotoEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  @ColumnInfo("time_stamp")
  val timeStamp: Long = System.currentTimeMillis() + Random(100).nextInt(),
  @ColumnInfo("photo_data")
  var photoData: PhotoData
)
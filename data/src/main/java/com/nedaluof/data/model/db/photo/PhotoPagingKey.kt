package com.nedaluof.data.model.db.photo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nedaluof.data.model.db.common.PagingKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Entity(tableName = "photo_paging_key")
data class PhotoPagingKey(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "photo_id")
  val photoId: Long,
  @ColumnInfo(name = "created_at")
  val createdAt: Long = System.currentTimeMillis()
) : PagingKey()
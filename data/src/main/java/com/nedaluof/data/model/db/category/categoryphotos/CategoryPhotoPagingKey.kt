package com.nedaluof.data.model.db.category.categoryphotos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nedaluof.data.model.db.common.PagingKey

/**
 * Created By NedaluOf - 7/29/2023.
 */
@Entity(tableName = "category_photo_paging_key")
data class CategoryPhotoPagingKey(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "category_id")
  val categoryId: Int,
  @ColumnInfo(name = "photo_id")
  val photoId: Long,
  @ColumnInfo(name = "created_at")
  val createdAt: Long = System.currentTimeMillis()
) : PagingKey()
package com.nedaluof.data.datasource.local.db.category.categoryphotos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoEntity

/**
 * Created By NedaluOf - 7/29/2023.
 */
@Dao
interface CategoryPhotosDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCategoryPhotosList(list: List<CategoryPhotoEntity>)

  @Query("SELECT * FROM category_photos_table WHERE category_id = :categoryId ORDER BY time_stamp ASC")
  fun loadCachedCategoryPhotosList(
    categoryId: Int
  ): PagingSource<Int, CategoryPhotoEntity>

  @Query("DELETE FROM category_photos_table")
  suspend fun clearCategoryPhotosTable()
}
package com.nedaluof.data.datasource.local.db.category.categoryphotos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoPagingKey

/**
 * Created By NedaluOf - 7/29/2023.
 */
@Dao
interface CategoryPhotosPagingKeysDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertCategoryPhotoPagingKeys(pagingKeys: List<CategoryPhotoPagingKey>)

  @Query("Select * From category_photo_paging_key Where category_id = :categoryId AND photo_id = :photoId")
  suspend fun getCategoryPhotoPagingKeyByIds(categoryId: Int, photoId: Long): CategoryPhotoPagingKey?

  @Query("Select created_at From category_photo_paging_key Order By created_at DESC LIMIT 1")
  suspend fun getLastCreationTime(): Long?

  @Query("Delete From category_photo_paging_key")
  suspend fun clearCategoryPhotoPagingKeyTable()
}
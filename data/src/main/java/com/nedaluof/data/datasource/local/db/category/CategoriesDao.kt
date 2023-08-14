package com.nedaluof.data.datasource.local.db.category

import androidx.room.Dao
import androidx.room.Query
import com.nedaluof.data.model.db.category.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/28/2023.
 */
@Dao
interface CategoriesDao {
  @Query("SELECT * FROM categories_table")
  fun loadCategoriesList(): Flow<List<CategoryEntity>>
}
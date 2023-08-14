package com.nedaluof.data.repository.categories

import com.nedaluof.data.model.db.category.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/22/2023.
 */
interface CategoriesRepository {

  /**
   * load categories list from cache
   * @return Flow<CategoryEntity>
   * */
  fun loadCategoriesList(): Flow<List<CategoryEntity>>

}
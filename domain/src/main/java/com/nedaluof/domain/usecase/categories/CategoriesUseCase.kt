package com.nedaluof.domain.usecase.categories

import com.nedaluof.domain.model.category.CategoryModel
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/28/2023.
 */
interface CategoriesUseCase {
  fun loadCategories(): Flow<List<CategoryModel>>
}
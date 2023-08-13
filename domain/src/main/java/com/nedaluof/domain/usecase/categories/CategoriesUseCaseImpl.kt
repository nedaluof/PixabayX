package com.nedaluof.domain.usecase.categories

import com.nedaluof.data.model.db.category.CategoryEntity
import com.nedaluof.data.repository.categories.CategoriesRepository
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.model.common.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/28/2023.
 */
class CategoriesUseCaseImpl @Inject constructor(
  private val repository: CategoriesRepository,
  private val mapper: Mapper<CategoryEntity, CategoryModel>
) : CategoriesUseCase {

  override fun loadCategories(): Flow<List<CategoryModel>> {
    return repository.loadCategoriesList().map {
      mapper.fromInputList(it)
    }
  }
}
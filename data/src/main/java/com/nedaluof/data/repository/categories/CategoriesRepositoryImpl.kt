package com.nedaluof.data.repository.categories

import com.nedaluof.data.datasource.local.db.PixabayXDatabase
import com.nedaluof.data.datasource.local.db.category.CategoriesDao
import com.nedaluof.data.model.db.category.CategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/28/2023.
 */
class CategoriesRepositoryImpl @Inject constructor(
  private val database: PixabayXDatabase
) : CategoriesRepository {

  private val categoriesDao: CategoriesDao
    get() = database.getCategoriesDao()

  override fun loadCategoriesList(): Flow<List<CategoryEntity>> = categoriesDao.loadCategoriesList()

  override suspend fun insertCategoriesEntitiesList(list: List<CategoryEntity>) {
    categoriesDao.insertCategoriesList(list)
  }
}
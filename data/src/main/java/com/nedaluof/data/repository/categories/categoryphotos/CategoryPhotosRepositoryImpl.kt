package com.nedaluof.data.repository.categories.categoryphotos

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.nedaluof.data.BuildConfig
import com.nedaluof.data.datasource.local.db.PixabayXDatabase
import com.nedaluof.data.datasource.local.db.category.categoryphotos.CategoryPhotosDao
import com.nedaluof.data.datasource.local.db.category.categoryphotos.CategoryPhotosPagingKeysDao
import com.nedaluof.data.datasource.remote.api.PixabayXApiServices
import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoEntity
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoPagingKey
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/29/2023.
 */
class CategoryPhotosRepositoryImpl @Inject constructor(
  private val api: PixabayXApiServices,
  private val database: PixabayXDatabase
) : CategoryPhotosRepository {

  private val categoryPhotosDao: CategoryPhotosDao
    get() = database.getCategoryPhotosDao()

  private val categoryPhotoPagingKeysDao: CategoryPhotosPagingKeysDao
    get() = database.getCategoryPhotosPagingKeysDao()

  override suspend fun loadCategoryPhotosList(
    category: String,
    page: Int
  ): Response<LoadPhotosResponse> =
    api.loadPhotosByCategory(BuildConfig.API_KEY, category, category, page)

  override fun loadCachedCategoryPhotosList(
    categoryId: Int
  ): PagingSource<Int, CategoryPhotoEntity> =
    categoryPhotosDao.loadCachedCategoryPhotosList(categoryId)

  override suspend fun <T> transactionBlock(block: suspend () -> T): T? =
    database.withTransaction(block)

  override suspend fun insertCategoryPhotosEntitiesList(list: List<CategoryPhotoEntity>) {
    categoryPhotosDao.insertCategoryPhotosList(list)
  }

  override suspend fun insertCategoryPhotoPagingKeys(pagingKeys: List<CategoryPhotoPagingKey>) {
    categoryPhotoPagingKeysDao.insertCategoryPhotoPagingKeys(pagingKeys)
  }

  override suspend fun getCategoryPhotoPagingKeyByIds(
    categoryId: Int,
    photoId: Long
  ): CategoryPhotoPagingKey? =
    categoryPhotoPagingKeysDao.getCategoryPhotoPagingKeyByIds(categoryId, photoId)
  

  override suspend fun getLastCreationTimeOfPagingKey(): Long? =
    categoryPhotoPagingKeysDao.getLastCreationTime()

  override suspend fun clearCategoryPhotosTable() {
    categoryPhotosDao.clearCategoryPhotosTable()
  }

  override suspend fun clearCategoryPhotoPagingKeysTable() {
    categoryPhotoPagingKeysDao.clearCategoryPhotoPagingKeyTable()
  }
}
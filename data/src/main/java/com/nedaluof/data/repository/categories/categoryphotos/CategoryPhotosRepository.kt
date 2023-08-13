package com.nedaluof.data.repository.categories.categoryphotos

import androidx.paging.PagingSource
import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoEntity
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoPagingKey
import retrofit2.Response

/**
 * Created By NedaluOf - 7/29/2023.
 */
interface CategoryPhotosRepository {
  /**
   * load category photos list from api
   * @param page
   * @return Response<LoadPhotosResponse>
   * */
  suspend fun loadCategoryPhotosList(
    category: String,
    page: Int
  ): Response<LoadPhotosResponse>

  /**
   * load category photo entities list from cache
   * @return PagingSource<Int, CategoryPhotoEntity>
   * */
  fun loadCachedCategoryPhotosList(
    categoryId : Int
  ): PagingSource<Int, CategoryPhotoEntity>

  /**
   * provide database transaction block to client
   * */
  suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T?

  /**
   * insert @param CategoryPhotoEntity [list] into category photos table
   * */
  suspend fun insertCategoryPhotosEntitiesList(list: List<CategoryPhotoEntity>)

  /**
   * insert @param [pagingKeys] list into category photo paging keys table
   * */
  suspend fun insertCategoryPhotoPagingKeys(pagingKeys: List<CategoryPhotoPagingKey>)

  /**
   * get category photo paging key by its @params [photoId] [categoryId]
   * */
  suspend fun getCategoryPhotoPagingKeyByIds(
    categoryId: Int,
    photoId: Long
  ): CategoryPhotoPagingKey?

  /**
   * get category photo paging key by its @params [photoId] [categoryId]
   * */
  suspend fun getDefaultCategoryPhotoPagingKey(
    categoryId: Int,
  ): CategoryPhotoPagingKey

  /**
   * get last category photo paging key creation time
   * */
  suspend fun getLastCreationTimeOfPagingKey(): Long?

  /**
   * clear category photos table
   * */
  suspend fun clearCategoryPhotosTable()

  /**
   * clear category photo paging keys table
   * */
  suspend fun clearCategoryPhotoPagingKeysTable()
}
package com.nedaluof.domain.usecase.categories.categoryphoto

import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoEntity
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoPagingKey
import com.nedaluof.data.repository.categories.categoryphotos.CategoryPhotosRepository
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.usecase.base.BaseRemoteMediator
import retrofit2.Response

/**
 * Created By NedaluOf - 7/29/2023.
 */
class CategoryPhotosRemoteMediator(
  private val category: CategoryModel,
  private val repository: CategoryPhotosRepository
) :
  BaseRemoteMediator<CategoryPhotoEntity, CategoryPhotoPagingKey, LoadPhotosResponse, PhotoData>() {


  override suspend fun getLastCreationTimeOfPagingKey() =
    repository.getLastCreationTimeOfPagingKey()

  override suspend fun getPagingKeyByEntity(entity: CategoryPhotoEntity) =
    repository.getCategoryPhotoPagingKeyByIds(entity.categoryId, entity.photoData.id)

  override suspend fun requestApi(page: Int) =
    repository.loadCategoryPhotosList(category.name, page)

  override suspend fun getDataListFromResponse(response: Response<LoadPhotosResponse>) =
    response.body()?.hits

  override suspend fun <T> transactionBlock(block: suspend () -> T) =
    repository.transactionBlock(block)

  override suspend fun clearDataAndPagingKeysTables() {
    repository.clearCategoryPhotoPagingKeysTable()
    repository.clearCategoryPhotosTable()
  }

  override suspend fun insertNewData(
    data: List<PhotoData>,
    nextKey: Int?,
    prevKey: Int?,
    currentPage: Int
  ) {
    val pagingKeys = data.map { photoData ->
      CategoryPhotoPagingKey(
        categoryId = category.id,
        photoId = photoData.id,
      ).also {
        it.prevKey = prevKey
        it.currentPage = currentPage
        it.nextKey = nextKey
      }
    }

    repository.insertCategoryPhotoPagingKeys(pagingKeys)
    repository.insertCategoryPhotosEntitiesList(data.map {
      CategoryPhotoEntity(categoryId = category.id, photoData = it)
    })
  }
}
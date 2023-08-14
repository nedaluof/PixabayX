package com.nedaluof.domain.usecase.photos

import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.model.db.photo.PhotoEntity
import com.nedaluof.data.model.db.photo.PhotoPagingKey
import com.nedaluof.data.repository.popularphotos.PopularPhotosRepository
import com.nedaluof.domain.usecase.base.BaseRemoteMediator
import retrofit2.Response

/**
 * Created By NedaluOf - 7/22/2023.
 */
class PhotosRemoteMediator(
  private val repository: PopularPhotosRepository
) : BaseRemoteMediator<PhotoEntity, PhotoPagingKey, LoadPhotosResponse, PhotoData>() {

  override suspend fun getLastCreationTimeOfPagingKey() =
    repository.getLastCreationTimeOfPagingKey()

  override suspend fun getPagingKeyByEntity(entity: PhotoEntity) =
    repository.getPhotoPagingKeyById(entity.photoData.id)

  override suspend fun requestApi(
    page: Int
  ) = repository.loadPhotoList(page)

  override suspend fun getDataListFromResponse(response: Response<LoadPhotosResponse>) =
    response.body()?.hits

  override suspend fun <T> transactionBlock(block: suspend () -> T) =
    repository.transactionBlock(block)

  override suspend fun clearDataAndPagingKeysTables() {
    repository.clearPagingKeysTable()
    repository.clearPhotosTable()
  }

  override suspend fun insertNewData(
    data: List<PhotoData>,
    nextKey: Int?,
    prevKey: Int?,
    currentPage: Int
  ) {
    val pagingKeys = data.map { photoData ->
      PhotoPagingKey(
        photoId = photoData.id
      ).also {
        it.prevKey = prevKey
        it.currentPage = currentPage
        it.nextKey = nextKey
      }
    }
    repository.insertPhotoPagingKeys(pagingKeys)
    repository.insertPhotoEntitiesList(data.map { PhotoEntity(photoData = it) })
  }
}
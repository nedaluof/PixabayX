package com.nedaluof.data.repository.popularphotos

import androidx.paging.PagingSource
import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.db.photo.PhotoEntity
import com.nedaluof.data.model.db.photo.PhotoPagingKey
import retrofit2.Response

/**
 * Created By NedaluOf - 7/22/2023.
 */
interface PopularPhotosRepository {
  /**
   * load photo list from pixabay
   * @param page
   * @return Response<LoadPhotosResponse>
   * */
  suspend fun loadPhotoList(page: Int): Response<LoadPhotosResponse>

  /**
   * load photo entities list from cache
   * @return PagingSource<Int, PhotoEntity>
   * */
  fun loadCachedPhotoList(): PagingSource<Int, PhotoEntity>

  /**
   * provide database transaction block to client
   * */
  suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T?

  /**
   * insert @param PhotoEntity [list] into photos table
   * */
  suspend fun insertPhotoEntitiesList(list: List<PhotoEntity>)

  /**
   * insert @param [pagingKeys] list into paging keys table
   * */
  suspend fun insertPhotoPagingKeys(pagingKeys: List<PhotoPagingKey>)

  /**
   * get paging key by its @param [id]
   * */
  suspend fun getPhotoPagingKeyById(id: Long): PhotoPagingKey?

  /**
   * get last paging key creation time
   * */
  suspend fun getLastCreationTimeOfPagingKey(): Long?

  /**
   * clear photos table
   * */
  suspend fun clearPhotosTable()

  /**
   * clear paging keys table
   * */
  suspend fun clearPagingKeysTable()
}
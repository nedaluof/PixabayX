package com.nedaluof.data.repository.popularphotos

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.nedaluof.data.BuildConfig
import com.nedaluof.data.datasource.local.db.PixabayXDatabase
import com.nedaluof.data.datasource.local.db.photo.PhotosDao
import com.nedaluof.data.datasource.local.db.photo.PhotosPagingKeysDao
import com.nedaluof.data.datasource.remote.api.PixabayXApiServices
import com.nedaluof.data.model.apiresponse.LoadPhotosResponse
import com.nedaluof.data.model.db.photo.PhotoEntity
import com.nedaluof.data.model.db.photo.PhotoPagingKey
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/22/2023.
 */
class PopularPhotosRepositoryImpl @Inject constructor(
  private val api: PixabayXApiServices,
  private val database: PixabayXDatabase
) : PopularPhotosRepository {

  private val photosDao: PhotosDao
    get() = database.getPhotosDao()

  private val photosPagingKeysDao: PhotosPagingKeysDao
    get() = database.getPhotosPagingKeysDao()

  /**
   * load photo list from pixabay
   * @param page
   * @return Response<LoadPhotosResponse>
   * */
  override suspend fun loadPhotoList(page: Int): Response<LoadPhotosResponse> =
    api.loadPopularPhotos(BuildConfig.API_KEY, page)

  /**
   * load photo entities list from cache
   * @return PagingSource<Int, PhotoEntity>
   * */
  override fun loadCachedPhotoList(): PagingSource<Int, PhotoEntity> =
    photosDao.loadPhotosList()

  /**
   * provide database transaction block to client
   * */
  override suspend fun <T> transactionBlock(
    block: suspend () -> T
  ): T? = database.withTransaction(block)

  /**
   * insert @param PhotoEntity [list] into photos table
   * */
  override suspend fun insertPhotoEntitiesList(list: List<PhotoEntity>) =
    photosDao.insertPhotosList(list)

  /**
   * insert @param [pagingKeys] list into paging keys table
   * */
  override suspend fun insertPhotoPagingKeys(pagingKeys: List<PhotoPagingKey>) =
    photosPagingKeysDao.insertPhotoPagingKeys(pagingKeys)

  /**
   * get paging key by its @param [id]
   * */
  override suspend fun getPhotoPagingKeyById(id: Long): PhotoPagingKey? =
    photosPagingKeysDao.getPhotoPagingKeyById(id)

  /**
   * get last paging key creation time
   * */
  override suspend fun getLastCreationTimeOfPagingKey(): Long? =
    photosPagingKeysDao.getLastCreationTime()

  /**
   * clear photos table
   * */
  override suspend fun clearPhotosTable() = photosDao.clearPhotosTable()

  /**
   * clear paging keys table
   * */
  override suspend fun clearPagingKeysTable() = photosPagingKeysDao.clearPhotosPagingKeyTable()
}
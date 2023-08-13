package com.nedaluof.data.datasource.local.db.photo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.data.model.db.photo.PhotoPagingKey

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Dao
interface PhotosPagingKeysDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPhotoPagingKeys(pagingKeys: List<PhotoPagingKey>)

  @Query("Select * From photo_paging_key Where photo_id = :photoId")
  suspend fun getPhotoPagingKeyById(photoId: Long): PhotoPagingKey?

  @Query("Delete From photo_paging_key")
  suspend fun clearPhotosPagingKeyTable()

  @Query("Select created_at From photo_paging_key Order By created_at DESC LIMIT 1")
  suspend fun getLastCreationTime(): Long?
}
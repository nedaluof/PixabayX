package com.nedaluof.data.datasource.local.db.photo

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nedaluof.data.model.db.photo.PhotoEntity

/**
 * Created By NedaluOf - 7/14/2023.
 */
@Dao
interface PhotosDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPhotosList(list: List<PhotoEntity>)

  @Query("SELECT * FROM photos_table ORDER BY time_stamp ASC")
  fun loadPhotosList(): PagingSource<Int, PhotoEntity>

  @Query("DELETE FROM photos_table")
  suspend fun clearPhotosTable()
}
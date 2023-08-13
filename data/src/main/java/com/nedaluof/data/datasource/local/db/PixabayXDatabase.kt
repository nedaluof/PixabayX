package com.nedaluof.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nedaluof.data.datasource.local.db.category.CategoriesDao
import com.nedaluof.data.datasource.local.db.category.categoryphotos.CategoryPhotosDao
import com.nedaluof.data.datasource.local.db.category.categoryphotos.CategoryPhotosPagingKeysDao
import com.nedaluof.data.datasource.local.db.photo.PhotosDao
import com.nedaluof.data.datasource.local.db.photo.PhotosPagingKeysDao
import com.nedaluof.data.model.db.category.CategoryEntity
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoEntity
import com.nedaluof.data.model.db.category.categoryphotos.CategoryPhotoPagingKey
import com.nedaluof.data.model.db.photo.PhotoDataTypeConverter
import com.nedaluof.data.model.db.photo.PhotoEntity
import com.nedaluof.data.model.db.photo.PhotoPagingKey

/**
 * Created By NedaluOf - 7/22/2023.
 */
@Database(
  entities = [
    CategoryEntity::class,
    PhotoEntity::class,
    PhotoPagingKey::class,
    CategoryPhotoEntity::class,
    CategoryPhotoPagingKey::class
  ],
  version = 1,
  exportSchema = false
)
@TypeConverters(PhotoDataTypeConverter::class)
abstract class PixabayXDatabase : RoomDatabase() {

  /**
   * Photos related Dao's
   * */
  abstract fun getPhotosDao(): PhotosDao
  abstract fun getPhotosPagingKeysDao(): PhotosPagingKeysDao

  /**
   * Categories Dao
   * */
  abstract fun getCategoriesDao(): CategoriesDao

  /**
   * Categories Photos related Dao's
   * */
  abstract fun getCategoryPhotosDao(): CategoryPhotosDao
  abstract fun getCategoryPhotosPagingKeysDao(): CategoryPhotosPagingKeysDao

  companion object {
    const val DATABASE_NAME = "pixabay_x_database"
    const val PRE_POPULATE_DATABASE_PATH = "database/pixabay_x_pre_populate.db"
  }
}
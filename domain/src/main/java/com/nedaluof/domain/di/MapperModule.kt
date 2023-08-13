package com.nedaluof.domain.di

import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.model.db.category.CategoryEntity
import com.nedaluof.domain.model.category.CategoryMapper
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.photos.PhotoMapper
import com.nedaluof.domain.model.photos.PhotoModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created By NedaluOf - 7/22/2023.
 */
@InstallIn(ViewModelComponent::class)
@Module
abstract class MapperModule {

  @ViewModelScoped
  @Binds
  abstract fun bindPhotoMapper(
    mapperImpl: PhotoMapper
  ): Mapper<PhotoData, PhotoModel>

  @ViewModelScoped
  @Binds
  abstract fun bindCategoryMapper(
    mapperImpl: CategoryMapper
  ): Mapper<CategoryEntity, CategoryModel>
}
package com.nedaluof.data.di

import com.nedaluof.data.repository.categories.CategoriesRepository
import com.nedaluof.data.repository.categories.CategoriesRepositoryImpl
import com.nedaluof.data.repository.categories.categoryphotos.CategoryPhotosRepository
import com.nedaluof.data.repository.categories.categoryphotos.CategoryPhotosRepositoryImpl
import com.nedaluof.data.repository.popularphotos.PopularPhotosRepository
import com.nedaluof.data.repository.popularphotos.PopularPhotosRepositoryImpl
import com.nedaluof.data.repository.search.SearchForPhotosRepository
import com.nedaluof.data.repository.search.SearchForPhotosRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By NedaluOf - 7/22/2023.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun bindPhotosRepository(
    repositoryImpl: PopularPhotosRepositoryImpl
  ): PopularPhotosRepository

  @Singleton
  @Binds
  abstract fun bindCategoriesRepository(
    repositoryImpl: CategoriesRepositoryImpl
  ): CategoriesRepository

  @Singleton
  @Binds
  abstract fun bindCategoryPhotosRepository(
    repositoryImpl: CategoryPhotosRepositoryImpl
  ): CategoryPhotosRepository

  @Singleton
  @Binds
  abstract fun bindSearchForPhotosRepository(
    repositoryImpl: SearchForPhotosRepositoryImpl
  ): SearchForPhotosRepository
}
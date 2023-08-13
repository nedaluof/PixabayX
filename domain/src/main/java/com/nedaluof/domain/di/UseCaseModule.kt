package com.nedaluof.domain.di

import com.nedaluof.domain.usecase.categories.CategoriesUseCase
import com.nedaluof.domain.usecase.categories.CategoriesUseCaseImpl
import com.nedaluof.domain.usecase.categories.categoryphoto.CategoryPhotosUseCase
import com.nedaluof.domain.usecase.categories.categoryphoto.CategoryPhotosUseCaseImpl
import com.nedaluof.domain.usecase.photos.PhotosUseCase
import com.nedaluof.domain.usecase.photos.PhotosUseCaseImpl
import com.nedaluof.domain.usecase.search.SearchForPhotosUseCase
import com.nedaluof.domain.usecase.search.SearchForPhotosUseCaseImpl
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
abstract class UseCaseModule {

  @ViewModelScoped
  @Binds
  abstract fun bindPhotosUseCase(
    useCaseImpl: PhotosUseCaseImpl
  ): PhotosUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindCategoriesUseCase(
    useCaseImpl: CategoriesUseCaseImpl
  ): CategoriesUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindCategoryPhotosUseCase(
    useCaseImpl: CategoryPhotosUseCaseImpl
  ): CategoryPhotosUseCase

  @ViewModelScoped
  @Binds
  abstract fun bindSearchForPhotosUseCase(
    useCaseImpl: SearchForPhotosUseCaseImpl
  ): SearchForPhotosUseCase
}
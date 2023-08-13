package com.nedaluof.domain.usecase.categories.categoryphoto

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nedaluof.data.model.apiresponse.PhotoData
import com.nedaluof.data.repository.categories.categoryphotos.CategoryPhotosRepository
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.model.common.Mapper
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/29/2023.
 */
class CategoryPhotosUseCaseImpl @Inject constructor(
  private val repository: CategoryPhotosRepository,
  private val mapper: Mapper<PhotoData, PhotoModel>
) : CategoryPhotosUseCase {

  @OptIn(ExperimentalPagingApi::class)
  override fun loadCategoryPhotos(
    category : CategoryModel
  ): Flow<PagingData<PhotoModel>> {
    return Pager(
      config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { repository.loadCachedCategoryPhotosList(category.id) },
      remoteMediator = CategoryPhotosRemoteMediator(category, repository)
    ).flow.map {
      it.map { categoryPhotoEntity ->
        mapper.fromInput(categoryPhotoEntity.photoData)
      }
    }
  }
}
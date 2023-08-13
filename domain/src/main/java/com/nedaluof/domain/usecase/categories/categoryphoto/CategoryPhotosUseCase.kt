package com.nedaluof.domain.usecase.categories.categoryphoto

import androidx.paging.PagingData
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/29/2023.
 */
interface CategoryPhotosUseCase {
  fun loadCategoryPhotos(
    category : CategoryModel
  ) : Flow<PagingData<PhotoModel>>
}
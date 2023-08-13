package com.nedaluof.domain.usecase.photos

import androidx.paging.PagingData
import com.nedaluof.domain.model.photos.PhotoModel
import kotlinx.coroutines.flow.Flow

/**
 * Created By NedaluOf - 7/22/2023.
 */
interface PhotosUseCase {
  fun loadPhotosList() : Flow<PagingData<PhotoModel>>
}
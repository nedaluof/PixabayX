package com.nedaluof.pixabayx.ui.main.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nedaluof.domain.usecase.photos.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/31/2023.
 */
@HiltViewModel
class PopularPhotosViewModel @Inject constructor(
  popularPhotosUseCase: PhotosUseCase
) : ViewModel() {

  //region functions
  val photos = popularPhotosUseCase.loadPhotosList()
    .cachedIn(viewModelScope)
  //endregion
}
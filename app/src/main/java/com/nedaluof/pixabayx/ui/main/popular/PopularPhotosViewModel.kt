package com.nedaluof.pixabayx.ui.main.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.domain.usecase.photos.PhotosUseCase
import com.nedaluof.pixabayx.utils.ext.postDelayed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/31/2023.
 */
@HiltViewModel
class PopularPhotosViewModel @Inject constructor(
  private val popularPhotosUseCase: PhotosUseCase
) : ViewModel() {

  //region variables
  private val _popularPhotos = MutableStateFlow<PagingData<PhotoModel>?>(null)
  val popularPhotos = _popularPhotos.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  private fun loadPhotos(){
    _loading.value = true
    viewModelScope.launch {
      popularPhotosUseCase.loadPhotosList()
        .cachedIn(viewModelScope)
        .collectLatest {
          if (_loading.value) {
            { _loading.value = false }.postDelayed(1000)
          }
          _popularPhotos.value = it
        }
    }
  }
  //endregion

  init {
    loadPhotos()
  }
}
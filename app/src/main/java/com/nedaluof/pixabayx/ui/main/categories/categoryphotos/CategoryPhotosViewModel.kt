package com.nedaluof.pixabayx.ui.main.categories.categoryphotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.domain.usecase.categories.categoryphoto.CategoryPhotosUseCase
import com.nedaluof.pixabayx.utils.ext.postDelayed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/27/2023.
 */
@HiltViewModel
class CategoryPhotosViewModel @Inject constructor(
  private val categoryPhotosUseCase: CategoryPhotosUseCase
) : ViewModel() {

  //region variables
  private val _photos = MutableStateFlow<PagingData<PhotoModel>?>(null)
  val photos = _photos.asStateFlow()

  private val _loading = MutableStateFlow(false)
  val loading = _loading.asStateFlow()

  private var isLoadedForFirstTime = false
  //endregion

  //region functions
  fun loadPhotosByCategory(
    category: CategoryModel
  ) {
    if (!isLoadedForFirstTime) {
      _loading.value = true
      viewModelScope.launch {
        if (_loading.value) {
          { _loading.value = false }.postDelayed(1000)
        }
        isLoadedForFirstTime = true
        categoryPhotosUseCase.loadCategoryPhotos(category).cachedIn(this).collectLatest {
          _photos.value = it
        }
      }
    }
  }
  //endregion
}
package com.nedaluof.pixabayx.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.domain.usecase.search.SearchForPhotosUseCase
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
class SearchPhotosViewModel @Inject constructor(
  private val useCase: SearchForPhotosUseCase
) : ViewModel() {

  //region variables
  private val _searchPhotos = MutableStateFlow<PagingData<PhotoModel>?>(null)
  val searchPhotos = _searchPhotos.asStateFlow()
  //endregion

  //region
  fun searchForPhotos(
    query: String
  ) {
    _searchPhotos.value = PagingData.empty()
    viewModelScope.launch {
      useCase.searchForPhotosByQuery(query).collectLatest {
        _searchPhotos.value = it
      }
    }
  }
  //endregion
}
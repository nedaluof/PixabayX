package com.nedaluof.pixabayx.ui.main.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.domain.usecase.categories.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/28/2023.
 */
@HiltViewModel
class CategoriesViewModel @Inject constructor(
  private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

  //region variables
  private val _categories = MutableStateFlow<List<CategoryModel>>(emptyList())
  val categories = _categories.asStateFlow()
  //endregion

  //region methods
  private fun loadCategories() {
    viewModelScope.launch {
      categoriesUseCase.loadCategories().collectLatest {
        _categories.value = it
      }
    }
  }
  //endregion

  init {
    loadCategories()
  }
}
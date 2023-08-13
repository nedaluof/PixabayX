package com.nedaluof.pixabayx.ui.main.categories.categoryphotos

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.FragmentCategoryPhotosBinding
import com.nedaluof.pixabayx.ui.base.BaseFragment
import com.nedaluof.pixabayx.ui.base.PagingLoadStateAdapter
import com.nedaluof.pixabayx.utils.ui.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import java.util.concurrent.TimeUnit

/**
 * Created By NedaluOf - 7/27/2023.
 */
@AndroidEntryPoint
class CategoryPhotosFragment :
  BaseFragment<FragmentCategoryPhotosBinding>(R.layout.fragment_category_photos) {

  //region variables
  private val categoryPhotosViewModel by viewModels<CategoryPhotosViewModel>()
  private var photosPagedAdapter: CategoryPhotosAdapter? = null
  private var photosJob: Job? = null
  private val categoryArgs: CategoryPhotosFragmentArgs by navArgs()
  private val categoryModel: CategoryModel
    get() = categoryArgs.category!!
  //end region

  //region view related
  override fun onBindingReady() {
    with(binding) {
      ViewCompat.requestApplyInsets(categoryPhotosLayout)
      ViewCompat.requestApplyInsets(photosRecyclerView)
      val transitionViews: Array<Pair<View, String>> = arrayOf(
        categoryName to categoryModel.name,
        categoryImage to categoryModel.coverImage,
      )
      transitionViews.forEach { pair ->
        ViewCompat.setTransitionName(pair.first, pair.second)
      }
    }
    postponeEnterTransition(200, TimeUnit.MILLISECONDS)
  }

  override fun initViews() {
    with(binding) {
      category = categoryModel
      lifecycleOwner = this@CategoryPhotosFragment
      executePendingBindings()
    }
    initPhotosRecyclerView()
  }

  private fun initPhotosRecyclerView() {
    photosPagedAdapter =
      CategoryPhotosAdapter { photo, transitionViewsPairs ->
        val direction =
          CategoryPhotosFragmentDirections.actionCategoryPhotosFragmentToPhotoDetailsFragment(photo)
        val extras = FragmentNavigatorExtras(*transitionViewsPairs)
        findNavController().navigate(direction, extras)
      }.apply {
        with(binding) {
          photosRecyclerView.adapter = withLoadStateFooter(
            footer = PagingLoadStateAdapter { this@apply.retry() }
          )

          addLoadStateListener { loadState ->
            val error = when {
              loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
              loadState.append is LoadState.Error -> loadState.append as LoadState.Error
              loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
              else -> null
            }
            error?.let {
              if (snapshot().isEmpty()) {
                requireActivity().toast(error.error.message.toString())
              }
            }
          }
          postponeEnterTransition()
          photosRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
          }
        }
      }
  }
  //endregion

  //region listen to view model and data
  override fun loadData() {
    categoryPhotosViewModel.loadPhotosByCategory(categoryModel)
  }

  override fun observeViewModel() {
    photosJob?.cancel()
    photosJob = categoryPhotosViewModel.photos.collectFlow { data ->
      data?.let {
        photosPagedAdapter?.submitData(data)
      }
    }
  }
  //endregion
}
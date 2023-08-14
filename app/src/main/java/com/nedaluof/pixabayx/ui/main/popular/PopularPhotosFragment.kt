package com.nedaluof.pixabayx.ui.main.popular

import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.FragmentPopularPhotosBinding
import com.nedaluof.pixabayx.ui.base.BaseFragment
import com.nedaluof.pixabayx.ui.base.PagingLoadStateAdapter
import com.nedaluof.pixabayx.utils.ui.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

/**
 * Created By NedaluOf - 7/29/2023.
 */
@AndroidEntryPoint
class PopularPhotosFragment :
  BaseFragment<FragmentPopularPhotosBinding>(R.layout.fragment_popular_photos) {

  //region variables
  private val popularPhotosViewModel by viewModels<PopularPhotosViewModel>()
  private var photosPagedAdapter: PopularPhotosAdapter? = null
  private var photosJob: Job? = null
  //end region

  //region view related

  override fun onBindingReady() {
    with(binding) {
      viewModel = popularPhotosViewModel
      lifecycleOwner = viewLifecycleOwner
      executePendingBindings()
    }
  }

  override fun initViews() {
    initPhotosRecyclerView()
  }

  private fun initPhotosRecyclerView() {
    photosPagedAdapter =
      PopularPhotosAdapter { photo, transitionViewsPairs ->
        val direction =
          PopularPhotosFragmentDirections.actionPopularPhotosFragmentToPhotoDetailsFragment(photo)
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

  //region listen to view model
  override fun observeViewModel() {
    photosJob = popularPhotosViewModel.popularPhotos.collectFlow { data ->
      data?.let {
        photosPagedAdapter?.submitData(data)
      }
    }
  }
  //endregion


  override fun onDestroy() {
    super.onDestroy()
    photosJob?.cancel()
  }
}
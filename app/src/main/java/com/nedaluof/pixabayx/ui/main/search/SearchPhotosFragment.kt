package com.nedaluof.pixabayx.ui.main.search

import android.view.inputmethod.EditorInfo
import androidx.core.view.doOnPreDraw
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.FragmentSearchPhotosBinding
import com.nedaluof.pixabayx.ui.base.BaseFragment
import com.nedaluof.pixabayx.ui.base.PagingLoadStateAdapter
import com.nedaluof.pixabayx.utils.ext.hideKeyboard
import com.nedaluof.pixabayx.utils.ext.isNotEmptyOrBlank
import com.nedaluof.pixabayx.utils.ui.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created By NedaluOf - 7/31/2023.
 */
@AndroidEntryPoint
class SearchPhotosFragment :
  BaseFragment<FragmentSearchPhotosBinding>(R.layout.fragment_search_photos) {

  //region variables
  private val searchPhotosViewModel by viewModels<SearchPhotosViewModel>()
  private var searchPhotosAdapter: SearchPhotosAdapter? = null
  private var searchPhotosJob: Job? = null
  //end region

  //region view related
  override fun initViews() {
    initSearchEditText()
    initPhotosRecyclerView()
  }

  private fun initSearchEditText() {
    with(binding.searchEditText) {
      setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          val query = text.toString()
          if (query.isNotEmptyOrBlank()) {
            searchPhotosViewModel.searchForPhotos(query.trim())
          } else {
            resetAdapter()
          }
          view?.let {
            requireActivity().hideKeyboard(it)
          }
          clearFocus()
        }
        false
      }

      doAfterTextChanged { editable ->
        val query = text.toString()
        if (!query.isNotEmptyOrBlank()) {
          resetAdapter()
        }
      }
    }
  }

  private fun initPhotosRecyclerView() {
    searchPhotosAdapter =
      SearchPhotosAdapter { photo, transitionViewsPairs ->
        val direction =
          SearchPhotosFragmentDirections.actionSearchPhotosFragmentToPhotoDetailsFragment(photo)
        val extras = FragmentNavigatorExtras(*transitionViewsPairs)
        findNavController().navigate(direction, extras)
      }.apply {
        with(binding) {
          searchPhotosRecyclerView.adapter = withLoadStateFooter(
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
          searchPhotosRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
          }
        }
      }
  }

  private fun resetAdapter() {
    lifecycleScope.launch {
      searchPhotosAdapter?.submitData(PagingData.empty())
    }
  }
  //endregion

  //region listen to view model
  override fun observeViewModel() {
    searchPhotosJob = searchPhotosViewModel.searchPhotos.collectStateFlow { data ->
      data?.let {
        searchPhotosAdapter?.submitData(data)
      }
    }
  }
  //endregion

  override fun onDestroyView() {
    super.onDestroyView()
    searchPhotosJob?.cancel()
  }
}
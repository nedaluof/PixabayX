package com.nedaluof.pixabayx.ui.main.categories

import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.FragmentCategoriesBinding
import com.nedaluof.pixabayx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

/**
 * Created By NedaluOf - 7/28/2023.
 */
@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(R.layout.fragment_categories) {
  //region variables
  private val categoriesViewModel by viewModels<CategoriesViewModel>()
  private var categoriesAdapter: CategoriesAdapter? = null
  private var categoriesJob: Job? = null
  //end region

  //region view related
  override fun initViews() {
    initCategoriesRecyclerView()
  }

  private fun initCategoriesRecyclerView() {
    categoriesAdapter = CategoriesAdapter { category, transitionViewsPairs ->
      val direction =
        CategoriesFragmentDirections.actionCategoriesFragmentToCategoryPhotosFragment(category)
      val extras = FragmentNavigatorExtras(*transitionViewsPairs)
      findNavController().navigate(direction, extras)
    }
    with(binding) {
      categoriesRecyclerView.adapter = categoriesAdapter
      postponeEnterTransition()
      categoriesRecyclerView.doOnPreDraw {
        startPostponedEnterTransition()
      }
    }
  }
  //endregion

  //region listen to view model
  override fun observeViewModel() {
    categoriesJob?.cancel()
    categoriesJob = categoriesViewModel.categories.collectStateFlow { data ->
      categoriesAdapter?.submitList(data)
    }
  }
  //endregion
}
package com.nedaluof.pixabayx.ui.main.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nedaluof.domain.model.category.CategoryModel
import com.nedaluof.pixabayx.databinding.ItemCategoryBinding
import com.nedaluof.pixabayx.utils.ui.click

/**
 * Created By NedaluOf - 7/28/2023.
 */
class CategoriesAdapter(
  private val onItemClicked: (CategoryModel, Array<Pair<View, String>>) -> Unit
) : ListAdapter<CategoryModel, CategoriesAdapter.CategoryVH>(CategoryDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryVH(
    ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: CategoryVH, position: Int) {
    holder.bind(getItem(position)!!)
  }

  inner class CategoryVH(
    private val binding: ItemCategoryBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: CategoryModel) {
      with(binding) {
        val transitionViews: Array<Pair<View, String>> = arrayOf(
          categoryName to category.name,
          categoryImage to category.coverImage
        )
        transitionViews.forEach { pair ->
          ViewCompat.setTransitionName(pair.first, pair.second)
        }
        this.category = category
        executePendingBindings()
        cardView.click {
          onItemClicked(category, transitionViews)
        }
      }
    }
  }

  companion object{
    private class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
      override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel) =
        oldItem == newItem
    }
  }
}
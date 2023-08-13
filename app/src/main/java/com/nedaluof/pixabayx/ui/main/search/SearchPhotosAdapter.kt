package com.nedaluof.pixabayx.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.pixabayx.databinding.ItemPhotoBinding
import com.nedaluof.pixabayx.utils.ui.click

/**
 * Created By NedaluOf - 7/31/2023.
 */
class SearchPhotosAdapter(
  private val onItemClicked: (PhotoModel, Array<Pair<View, String>>) -> Unit
) : PagingDataAdapter<PhotoModel, SearchPhotosAdapter.PhotoVH>(PhotoDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoVH(
    ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
  )

  override fun onBindViewHolder(holder: PhotoVH, position: Int) {
    holder.bind(getItem(position)!!)
  }

  inner class PhotoVH(
    private val binding: ItemPhotoBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: PhotoModel) {
      with(binding) {
        val transitionViews : Array<Pair<View, String>> = arrayOf(
          photoImage to photo.url,
          likeImage to "likeImage_${photo.id}",
          likesCount to photo.likes.toString(),
          viewsImage to "viewsImage_${photo.id}",
          viewsCount to photo.views.toString(),
          downloadsImage to "downloadsImage_${photo.id}",
          downloadsCount to photo.downloads.toString()  ,
          ownerImage to photo.owner.profileImage,
          photoOwnerName to photo.owner.name,
        )
        transitionViews.forEach { pair ->
          ViewCompat.setTransitionName(pair.first, pair.second)
        }
        this.photo = photo
        executePendingBindings()
        cardView.click {
          onItemClicked(photo, transitionViews)
        }
      }
    }
  }

  companion object {
    private class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoModel>() {
      override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
        oldItem == newItem
    }
  }
}
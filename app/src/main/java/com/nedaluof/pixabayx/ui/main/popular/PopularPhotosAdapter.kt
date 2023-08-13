package com.nedaluof.pixabayx.ui.main.popular

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
class PopularPhotosAdapter(
  private val onItemClicked: (PhotoModel, Array<Pair<View, String>>) -> Unit
) : PagingDataAdapter<PhotoModel, PopularPhotosAdapter.PhotoVH>(PhotoDiffCallback()) {

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
        val transitionViews: Array<Pair<View, String>> = arrayOf(
          photoImage to photo.url,
          ownerImage to photo.owner.profileImage,
          photoOwnerName to photo.owner.name,
          likeImage to "likesLayout_${photo.id}",
          likesCount to photo.likes.toString(),
          viewsImage to "viewsLayout_${photo.id}",
          viewsCount to photo.views.toString(),
          downloadsImage to "downloadsLayout_${photo.id}",
          downloadsCount to photo.downloads.toString()
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

  private class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoModel>() {
    override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
      oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel) =
      oldItem == newItem
  }
}
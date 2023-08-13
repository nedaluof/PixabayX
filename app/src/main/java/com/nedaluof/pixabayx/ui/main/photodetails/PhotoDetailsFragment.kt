package com.nedaluof.pixabayx.ui.main.photodetails

import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nedaluof.domain.model.photos.PhotoModel
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.FragmentPhotoDetailsBinding
import com.nedaluof.pixabayx.service.DownloadPhotoService
import com.nedaluof.pixabayx.ui.base.BaseFragment
import com.nedaluof.pixabayx.utils.ui.click
import java.util.concurrent.TimeUnit

/**
 * Created By NedaluOf - 7/28/2023.
 */
class PhotoDetailsFragment :
  BaseFragment<FragmentPhotoDetailsBinding>(R.layout.fragment_photo_details) {

  private val photoArgs: PhotoDetailsFragmentArgs by navArgs()
  private val photoModel: PhotoModel
    get() = photoArgs.photo!!

  override fun onBindingReady() {
    with(binding) {
      val transitionViews = arrayOf(
        photoImage to photoModel.url,
        likeImage to "likeImage_${photoModel.id}",
        likesCount to photoModel.likes.toString(),
        viewsImage to "viewsImage_${photoModel.id}",
        viewsCount to photoModel.views.toString(),
        downloadsImage to "downloadsImage_${photoModel.id}",
        downloadsCount to photoModel.downloads.toString(),
        ownerImage to photoModel.owner.profileImage,
        photoOwnerName to photoModel.owner.name,
      )
      transitionViews.forEach { pair ->
        ViewCompat.setTransitionName(pair.first, pair.second)
      }
    }
    postponeEnterTransition(200, TimeUnit.MILLISECONDS)
  }

  override fun initViews() {
    with(binding) {
      photo = photoModel
      lifecycleOwner = this@PhotoDetailsFragment
      executePendingBindings()
    }
  }

  override fun initClicks() {
    with(binding) {
      backBtn.click {
        findNavController().popBackStack()
      }
      downloadBtn.click {
        requireActivity().startService(
          Intent(requireActivity(), DownloadPhotoService::class.java)
            .putExtra(DownloadPhotoService.PHOTO_DOWNLOAD_URL, photoModel.downloadLink)
        )
      }
    }
  }
}
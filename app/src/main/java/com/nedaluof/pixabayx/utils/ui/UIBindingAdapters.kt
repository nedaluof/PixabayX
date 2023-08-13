package com.nedaluof.pixabayx.utils.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.request.CachePolicy
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.utils.ext.isNotEmptyOrBlank
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created By NedaluOf - 7/7/2023.
 */
object UIBindingAdapters {

  @BindingAdapter("ctrlVisibility")
  @JvmStatic
  fun View.controlVisibility(
    show: Boolean
  ) {
    isVisible = show
  }

  @BindingAdapter("loadUrl")
  @JvmStatic
  fun ImageView.loadImageUrl(
    url: String?
  ) {
    url?.let { urlString ->
      if (urlString.isNotEmptyOrBlank()) {
        val ctx = this.context
        this.load(urlString) {
          diskCachePolicy(CachePolicy.ENABLED)
          memoryCachePolicy(CachePolicy.ENABLED)
          networkCachePolicy(CachePolicy.ENABLED)
          placeholder(CircularProgressDrawable(ctx).apply {
            setColorSchemeColors(ContextCompat.getColor(context, R.color.pi_piquant))
            strokeWidth = 5f
            centerRadius = 30f
            start()
          })
        }
      }else{
        this.isVisible = false
      }
    }
  }

  @BindingAdapter("formatDate")
  @JvmStatic
  fun TextView.formatDate(
    dateString: String?
  ) {
    dateString?.let {
      val formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY")
      val now = LocalDateTime.parse(it.replace("Z", ""))
      text = now.format(formatter)
    }
  }
}
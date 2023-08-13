package com.nedaluof.pixabayx.utils.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import com.nedaluof.pixabayx.utils.ext.postDelayed

/**
 * Created By NedaluOf - 7/8/2023.
 */

fun View.click(
  block: () -> Unit
) {
  setOnClickListener {
    isEnabled = false
    block();
    {isEnabled = true}.postDelayed(2000)
  }
}

fun Context.toast(
  message: String
) {
  Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
package com.nedaluof.pixabayx.utils.ext

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created By NedaluOf - 7/22/2023.
 */
fun (() -> Unit).postDelayed(
  time: Long = 1500
) {
  Handler(Looper.getMainLooper()).postDelayed(this, time)
}

fun Context.isNetworkOk(): Boolean {
  val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE)
      as ConnectivityManager
  return run {
    val capabilities =
      connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
      when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
          true
        }

        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
          true
        }

        else -> capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
      }
    } else {
      false
    }
  }
}

fun String.isNotEmptyOrBlank() =
  this.isNotEmpty() && this.isNotBlank()

fun Context.hideKeyboard(view: View) {
  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
package com.nedaluof.domain.utils

import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created By NedaluOf - 7/29/2023.
 */

fun Exception.exceptionMessage() = when (this) {
  is IOException -> "IOException -> $message"
  is TimeoutException -> "TimeoutException -> $message"
  is CancellationException -> "CancellationException -> $message"
  is ArrayIndexOutOfBoundsException -> "ArrayIndexOutOfBoundsException -> $message"
  else -> "Exception -> $message"
}

fun log(message: String?) {
  Timber.e("NedaluOf -> $message")
}
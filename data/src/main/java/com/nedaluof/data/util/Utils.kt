package com.nedaluof.data.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created By NedaluOf - 8/11/2023.
 */


/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioScope(
  block: suspend () -> Unit
) {
  CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
    val job = async {
      block()
    }
    job.await()
  }
}
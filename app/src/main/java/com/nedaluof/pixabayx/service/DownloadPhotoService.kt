package com.nedaluof.pixabayx.service

import android.app.DownloadManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.os.IBinder
import com.nedaluof.domain.utils.log
import com.nedaluof.pixabayx.utils.ext.isNetworkOk
import com.nedaluof.pixabayx.utils.ui.toast
import kotlin.random.Random

/**
 * Created By NedaluOf - 7/28/2023.
 */
class DownloadPhotoService : Service() {

  // unique id for the being sura downloaded
  var downloadId: Long = 0
  private lateinit var photoDownloadUrl: String
  override fun onBind(intent: Intent?): IBinder? = null

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    photoDownloadUrl = intent?.getStringExtra(PHOTO_DOWNLOAD_URL)!!
    registerReceiver(
      onCompleteDownloadReceiver,
      IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
      RECEIVER_NOT_EXPORTED
    )
    startDownload()
    return START_NOT_STICKY
  }

  private fun startDownload() {
    if (this::photoDownloadUrl.isInitialized) {
      if (this.isNetworkOk()) {
        this.toast("Photo Download Started")
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadId = downloadManager.enqueue(buildDownloadRequest(photoDownloadUrl))
      } else {
        this.toast("No internet connection !")
        stopSelf()
      }
    } else {
      log("photoDownloadUrl not initialized")
      stopSelf()
    }
  }

  private fun buildDownloadRequest(
    photoDownloadUrl: String
  ): DownloadManager.Request {
    return DownloadManager.Request(Uri.parse(photoDownloadUrl))
      .setMimeType("image/jpeg")
      .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
      .setAllowedNetworkTypes(
        DownloadManager.Request.NETWORK_WIFI or
            DownloadManager.Request.NETWORK_MOBILE
      ).setTitle("Download PixabayX Photo")
      .setDescription("The selected photo being downloaded")
      .setDestinationInExternalPublicDir(
        Environment.DIRECTORY_DOWNLOADS,
        "PixabayX/image_${Random(2017)}_pixabay.jpeg"
      )
  }

  private val onCompleteDownloadReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
      // Checking if the received broadcast is for our enqueued download by matching download id
      if (downloadId == id) {
        context.toast("Photo Downloaded Successfully")
      } else {
        log("onReceive: download id not match")
      }
    }
  }

  override fun onDestroy() {
    unregisterReceiver(onCompleteDownloadReceiver)
    super.onDestroy()
  }

  companion object {
    const val PHOTO_DOWNLOAD_URL = "photo_download_url"
  }
}
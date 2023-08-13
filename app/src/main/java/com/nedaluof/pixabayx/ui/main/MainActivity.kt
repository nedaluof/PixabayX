package com.nedaluof.pixabayx.ui.main

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.ActivityMainBinding
import com.nedaluof.pixabayx.ui.base.BaseActivity
import com.nedaluof.pixabayx.utils.ui.click
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created By NedaluOf - 7/22/2023.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

  private lateinit var navigationController: NavController

  override fun doLogic() {
    navigationController =
      (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).navController
  }

  override fun initClicks() {
    with(binding) {
      popularBtn.click {
        if (::navigationController.isInitialized) {
          navigationController.navigate(R.id.navigate_to_popular_photos_fragment)
        }
      }
      searchBtn.click {
        if (::navigationController.isInitialized) {
          navigationController.navigate(R.id.navigate_to_search_photos_fragment)
        }
      }
    }
  }

  companion object {
    fun buildIntent(
      context: Context
    ) = Intent(context, MainActivity::class.java)
  }
}
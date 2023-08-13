package com.nedaluof.pixabayx.ui.intro

import com.nedaluof.pixabayx.R
import com.nedaluof.pixabayx.databinding.ActivityIntroBinding
import com.nedaluof.pixabayx.ui.base.BaseActivity
import com.nedaluof.pixabayx.ui.main.MainActivity
import com.nedaluof.pixabayx.utils.ext.postDelayed
/**
 * Created By NedaluOf - 7/22/2023.
 */
class IntroActivity : BaseActivity<ActivityIntroBinding>(R.layout.activity_intro) {

  override fun doLogic() {
    {
      startActivity(MainActivity.buildIntent(this))
      this.finish()
    }.postDelayed()
  }
}
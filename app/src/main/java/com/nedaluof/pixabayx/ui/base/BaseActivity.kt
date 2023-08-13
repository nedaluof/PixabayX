package com.nedaluof.pixabayx.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created By NedaluOf - 7/22/2023.
 */
abstract class BaseActivity<DB : ViewDataBinding>(
  private val layoutId: Int
) : AppCompatActivity() {

  private var _binding: DB? = null
  protected val binding: DB
    get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = DataBindingUtil.setContentView(this, layoutId)
    initViews()
    initClicks()
    doLogic()
    observeViewModel()
    loadData()
  }

  open fun initViews() {
    /**
     * used by child classes
     * */
  }

  open fun initClicks() {
    /**
     * used by child classes
     * */
  }

  open fun doLogic() {
    /**
     * used by child classes
     * */
  }

  open fun loadData() {
    /**
     * used by child classes
     * */
  }

  open fun observeViewModel() {
    /**
     * used by child classes
     * */
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
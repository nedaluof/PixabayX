package com.nedaluof.data.model.db.common

/**
 * Created By NedaluOf - 8/14/2023.
 */
open class PagingKey(
  var prevKey: Int? = null,
  var currentPage: Int = 0,
  var nextKey: Int? = null,
)
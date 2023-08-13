package com.nedaluof.domain.model.category

import com.nedaluof.data.model.db.category.CategoryEntity
import com.nedaluof.domain.model.common.Mapper
import javax.inject.Inject

/**
 * Created By NedaluOf - 7/28/2023.
 */
class CategoryMapper @Inject constructor() : Mapper<CategoryEntity, CategoryModel> {

  override fun fromInput(input: CategoryEntity) =
    with(CategoryModel()) {
      id = input.id
      name = input.name
      coverImage = input.coverImage
      this
    }
}
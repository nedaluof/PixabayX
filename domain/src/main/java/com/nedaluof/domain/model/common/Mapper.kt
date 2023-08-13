package com.nedaluof.domain.model.common

/**
 * Created By NedaluOf - 7/26/2023.
 */
interface Mapper<I, O> {
  fun fromInput(input: I): O
  fun fromInputList(inputList: List<I>): List<O> {
    return inputList.mapNotNull { fromInput(it) }
  }
}
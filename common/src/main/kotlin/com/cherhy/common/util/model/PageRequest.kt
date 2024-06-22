package com.cherhy.common.util.model

data class PageRequest(
    val page: Int,
    val size: Int,
) {
    val offset = (page - 1) * size.toLong()
}
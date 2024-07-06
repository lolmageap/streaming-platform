package com.cherhy.common.util.model

data class PageRequest(
    val page: Int,
    val size: Int,
    val sort: String,
    val direction: Direction,
) {
    val offset = (page - 1) * size.toLong()
}

enum class Direction {
    ASC, DESC,
}
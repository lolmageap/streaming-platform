package com.cherhy.common.util.model

data class PageRequest(
    val page: Page,
    val size: Size,
    val sort: Sort,
    val direction: Direction,
) {
    val offset = Offset.of(page, size)
}
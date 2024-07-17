package com.cherhy.common.util

import com.cherhy.common.util.model.Page
import com.cherhy.common.util.model.Size

class PageOffsetCalculator private constructor(
    val page: Page,
    val size: Size,
) {
    val limit = size.value
    val offset = (page.value - 1) * size.value
    companion object {
        fun of(
            page: Page,
            size: Size,
        ) = PageOffsetCalculator(
            page = page,
            size = size,
        )
    }
}
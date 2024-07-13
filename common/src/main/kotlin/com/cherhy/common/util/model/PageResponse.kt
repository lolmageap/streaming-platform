package com.cherhy.common.util.model

data class PageResponse <T> private constructor(
    val data: List<T>,
    val total: Long,
    val page: Page,
    val size: Size,
    val totalPages: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean,
) {
    companion object {
        fun <T> of(
            data: List<T>,
            total: Long,
            request: PageRequest,
        ) = PageResponse(
            data = data,
            total = total,
            page = request.page,
            size = request.size,
            totalPages = (total / request.size.value + if (total % request.size.value == 0L) 0 else 1).toInt(),
            hasNext = request.page.value < total / request.size.value,
            hasPrevious = request.page.value > 1,
        )
    }
}
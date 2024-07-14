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

        fun <T> of(
            data: List<T>,
            total: Long,
            page: Page,
            size: Size,
        ) = PageResponse(
            data = data,
            total = total,
            page = page,
            size = size,
            totalPages = (total / size.value + if (total % size.value == 0L) 0 else 1).toInt(),
            hasNext = page.value < total / size.value,
            hasPrevious = page.value > 1,
        )
    }
}
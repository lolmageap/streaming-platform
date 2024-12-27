package com.cherhy.usecase

import com.cherhy.common.util.model.Keyword
import com.cherhy.common.util.model.PageRequest
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PostCategory
import com.cherhy.domain.PostId
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.ReadPostService

class GetPostUseCase(
    private val readPostService: ReadPostService,
) {
    suspend fun execute(
        userId: UserId,
        postId: PostId,
    ) =
        reactiveTransaction {
            readPostService.get(
                userId,
                postId,
            )
        }

    suspend fun execute(
        userId: UserId,
        search: GetPostQuery,
        pageRequest: PageRequest,
    ) =
        reactiveTransaction {
            readPostService.getAll(
                userId,
                search.keyword,
                search.category,
                pageRequest.page,
                pageRequest.size,
            )
        }
}

data class GetPostQuery(
    val keyword: Keyword?,
    val category: PostCategory?,
)
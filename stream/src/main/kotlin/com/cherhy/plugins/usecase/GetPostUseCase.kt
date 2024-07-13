package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.PageRequest
import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.GetPostRequest
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.service.ReadPostService

class GetPostUseCase(
    private val readPostService: ReadPostService,
) {
    suspend fun execute(
        userId: UserId,
        postId: PostId,
    ) =
        readPostService.get(
            userId,
            postId,
        )

    suspend fun execute(
        userId: UserId,
        search: GetPostRequest,
        pageRequest: PageRequest,
    ) =
        readPostService.get(
            userId,
            search.keyword,
            search.category,
            pageRequest.page,
            pageRequest.size,
        )
}
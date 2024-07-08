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
    ) {
        TODO()
    }

    suspend fun execute(
        userId: UserId,
        getPostRequest: GetPostRequest,
        pageRequest: PageRequest,
    ) {
        TODO()
    }
}
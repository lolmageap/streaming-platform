package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.CreateVideoRequest
import com.cherhy.plugins.api.UpdatePostRequest
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.service.WritePostService

class UpdatePostUseCase(
    private val writePostService: WritePostService,
) {
    suspend fun execute(
        userId: UserId,
        postId: PostId,
        video: CreateVideoRequest,
        updatePostRequest: UpdatePostRequest,
    ) {
        TODO()
    }
}
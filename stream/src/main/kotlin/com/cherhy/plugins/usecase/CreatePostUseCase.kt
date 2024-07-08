package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.CreatePostRequest
import com.cherhy.plugins.api.CreateVideoRequest
import com.cherhy.plugins.service.WritePostService

class CreatePostUseCase(
    private val writePostService: WritePostService,
) {
    suspend fun execute(
        userId: UserId,
        video: CreateVideoRequest,
        createPostRequest: CreatePostRequest,
    ) {
        TODO()
    }
}
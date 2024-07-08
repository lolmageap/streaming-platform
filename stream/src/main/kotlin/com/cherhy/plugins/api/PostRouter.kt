package com.cherhy.plugins.api

import com.cherhy.common.util.Stream.Post.CREATE_POST
import com.cherhy.common.util.Stream.Post.DELETE_POST
import com.cherhy.common.util.Stream.Post.GET_POST
import com.cherhy.common.util.Stream.Post.GET_POSTS
import com.cherhy.common.util.Stream.Post.UPDATE_POST
import com.cherhy.common.util.model.PageRequest
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.usecase.CreatePostUseCase
import com.cherhy.plugins.usecase.GetPostUseCase
import com.cherhy.plugins.usecase.UpdatePostUseCase
import com.cherhy.plugins.util.extension.getQueryParams
import com.cherhy.plugins.util.extension.getVideo
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.post() {
    val getPostUseCase: GetPostUseCase by inject()
    val createPostUseCase: CreatePostUseCase by inject()
    val updatePostUseCase: UpdatePostUseCase by inject()
    val writePostService: WritePostService by inject()

    get(GET_POST) {
        val postId = call.pathVariable.postId
        val userId = call.request.userId
        val post = getPostUseCase.execute(userId, postId)

        call.respond(HttpStatusCode.OK, post)
    }

    get(GET_POSTS) {
        val pageRequest = call.getQueryParams<PageRequest>()
        val getPostRequest = call.getQueryParams<GetPostRequest>()
        val userId = call.request.userId
        val posts = getPostUseCase.execute(userId, getPostRequest, pageRequest)

        call.respond(HttpStatusCode.OK, posts)
    }

    post(CREATE_POST) {
        val userId = call.request.userId
        val video = call.getVideo()
        val createPostRequest = call.getQueryParams<CreatePostRequest>()

        createPostUseCase.execute(userId, video, createPostRequest)
        call.respond(HttpStatusCode.Created)
    }

    put(UPDATE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val video = call.getVideo()
        val updatePostRequest = call.getQueryParams<UpdatePostRequest>()

        updatePostUseCase.execute(userId, postId, video, updatePostRequest)
        call.respond(HttpStatusCode.OK)
    }

    delete(DELETE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        writePostService.delete(userId, postId)
        call.respond(HttpStatusCode.NoContent)
    }
}
package com.cherhy.api

import com.cherhy.common.util.Stream.Post.CREATE_POST
import com.cherhy.common.util.Stream.Post.DELETE_POST
import com.cherhy.common.util.Stream.Post.GET_POST
import com.cherhy.common.util.Stream.Post.GET_POSTS
import com.cherhy.common.util.Stream.Post.UPDATE_POST
import com.cherhy.common.util.model.PageRequest
import com.cherhy.usecase.CreatePostUseCase
import com.cherhy.usecase.DeletePostUseCase
import com.cherhy.usecase.GetPostUseCase
import com.cherhy.usecase.UpdatePostUseCase
import com.cherhy.util.extension.getVideo
import com.cherhy.util.extension.userId
import com.cherhy.util.postId
import extension.ktor.getQueryParams
import extension.ktor.pathVariable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.post() {
    val getPostUseCase by inject<GetPostUseCase>()
    val createPostUseCase by inject<CreatePostUseCase>()
    val updatePostUseCase by inject<UpdatePostUseCase>()
    val deletePostUseCase by inject<DeletePostUseCase>()

    get(GET_POST) {
        val postId = call.pathVariable.postId
        val userId = call.request.userId
        val post = getPostUseCase.execute(userId, postId)

        call.respond(HttpStatusCode.OK, post)
    }

    get(GET_POSTS) {
        val pageRequest = call.getQueryParams<PageRequest>()
        val search = call.getQueryParams<GetPostRequest>()
        val userId = call.request.userId
        val posts = getPostUseCase.execute(userId, search, pageRequest)

        call.respond(HttpStatusCode.OK, posts)
    }

    post(CREATE_POST) {
        val userId = call.request.userId
        val video = call.getVideo()
        val post = call.receive<CreatePostRequest>()

        createPostUseCase.execute(userId, video, post)
        call.respond(HttpStatusCode.Created)
    }

    put(UPDATE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val video = call.getVideo()
        val post = call.receive<UpdatePostRequest>()

        updatePostUseCase.execute(userId, postId, video, post)
        call.respond(HttpStatusCode.OK)
    }

    delete(DELETE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId

        deletePostUseCase.execute(userId, postId)
        call.respond(HttpStatusCode.NoContent)
    }
}
package com.cherhy.plugins.api

import com.cherhy.common.util.Stream.Post.CREATE_POST
import com.cherhy.common.util.Stream.Post.DELETE_POST
import com.cherhy.common.util.Stream.Post.GET_POST
import com.cherhy.common.util.Stream.Post.GET_POSTS
import com.cherhy.common.util.Stream.Post.UPDATE_POST
import com.cherhy.common.util.model.PageRequest
import com.cherhy.plugins.util.extension.getQueryParams
import com.cherhy.plugins.util.extension.getVideo
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.post() {
    get(GET_POST) {
        val postId = call.pathVariable.postId
        val userId = call.request.userId
        // TODO: Get post by postId and userId
    }

    get(GET_POSTS) {
        val pageRequest = call.getQueryParams<PageRequest>()
        val getPostRequest = call.getQueryParams<GetPostRequest>()
        val userId = call.request.userId
        // TODO: Get posts by pageRequest, getPostRequest and userId
    }

    post(CREATE_POST) {
        val userId = call.request.userId
        val createPostRequest = call.getQueryParams<CreatePostRequest>()
        val video = call.getVideo()
        // TODO: Create post by createPostRequest, video and userId
    }

    put(UPDATE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val updatePostRequest = call.getQueryParams<UpdatePostRequest>()
        val video = call.getVideo()
        // TODO: Update post by postId, updatePostRequest, video and userId
    }

    delete(DELETE_POST) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        // TODO: Delete post by postId and userId
    }
}
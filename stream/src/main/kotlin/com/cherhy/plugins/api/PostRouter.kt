package com.cherhy.plugins.api

import com.cherhy.common.util.Stream.Post.GET_POST
import com.cherhy.common.util.Stream.Post.GET_POSTS
import com.cherhy.common.util.model.PageRequest
import com.cherhy.plugins.util.extension.getQueryParams
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.post() {
    get(GET_POST) {
        val postId = call.pathVariable.postId
        val userId = call.userId
        // TODO: Get post by postId and userId
    }

    get(GET_POSTS) {
        val pageRequest = call.getQueryParams<PageRequest>()
        val getPostRequest = call.getQueryParams<GetPostRequest>()
        val userId = call.userId
        // TODO: Get posts by pageRequest, getPostRequest and userId
    }

    // TODO: create post


    // TODO: update post


    // TODO: delete post
}
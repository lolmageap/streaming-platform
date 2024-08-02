package com.cherhy.plugins

import com.cherhy.api.home
import com.cherhy.api.post
import com.cherhy.api.video
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        home()
        post()
        video()
    }
}
package com.cherhy.plugins.config

import com.cherhy.plugins.api.home
import com.cherhy.plugins.api.post
import com.cherhy.plugins.api.video
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        home()
        post()
        video()
    }
}
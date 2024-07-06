package com.cherhy.plugins.config

import com.cherhy.plugins.api.home
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        home()
    }
}
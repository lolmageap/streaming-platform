package com.cherhy.plugins

import com.cherhy.common.util.Stream.HOME
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get(HOME) {
            call.respondText("health check")
        }
    }
}
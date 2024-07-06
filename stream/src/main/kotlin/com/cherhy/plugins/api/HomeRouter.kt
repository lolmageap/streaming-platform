package com.cherhy.plugins.api

import com.cherhy.common.util.Stream.HOME
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home() {
    get(HOME) {
        call.respondText("health check")
    }
}
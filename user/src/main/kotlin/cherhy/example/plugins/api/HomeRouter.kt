package cherhy.example.plugins.api

import cherhy.example.plugins.util.constant.EndPoint
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home() {
    get(EndPoint.HOME) {
        call.respondText("health check")
    }
}
package cherhy.com.router

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.home() {
    get("/") {
        call.respondText("Hello World!")
    }
}
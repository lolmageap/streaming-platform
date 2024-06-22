package cherhy.example.plugins.config

import cherhy.example.plugins.api.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        home()
        login()
        user()
        swaggerUI("/swagger")
    }
}
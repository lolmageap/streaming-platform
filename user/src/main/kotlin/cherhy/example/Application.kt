package cherhy.example

import cherhy.example.plugins.config.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureJwt()
    configureJackson()
    configureDependencyInjection()
    configureDatabase()
}
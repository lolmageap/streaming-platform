package cherhy.com

import cherhy.com.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureDatabase()
    configureDependencyInjection()
    configureScheduler()
    healthCheckScheduler()
}

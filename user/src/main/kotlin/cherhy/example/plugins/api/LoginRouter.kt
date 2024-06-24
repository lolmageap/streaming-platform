package cherhy.example.plugins.api

import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.util.EndPoint.LOGIN
import cherhy.example.plugins.util.EndPoint.LOGOUT
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.login() {
    val loginUseCase: LoginUseCase by inject()

    get(LOGIN) {
        val loginRequest = call.receive<LoginRequest>()
        loginUseCase.execute(loginRequest)
    }

    delete(LOGOUT) {
        call.respondText("health check")
    }
}
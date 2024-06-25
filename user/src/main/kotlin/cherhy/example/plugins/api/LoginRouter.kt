package cherhy.example.plugins.api

import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.util.constant.EndPoint.LOGIN
import cherhy.example.plugins.util.constant.EndPoint.LOGOUT
import cherhy.example.plugins.util.extension.accessToken
import cherhy.example.plugins.util.extension.refreshToken
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.login() {
    val loginUseCase: LoginUseCase by inject()

    post(LOGIN) {
        val loginRequest = call.receive<LoginRequest>()
        val jwt = loginUseCase.execute(loginRequest)

        call.response.headers.accessToken = jwt.accessToken
        call.response.cookies.refreshToken = jwt.refreshToken
        call.respond(HttpStatusCode.Created)
    }

    delete(LOGOUT) {
        TODO("Not yet implemented")
    }
}
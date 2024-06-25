package cherhy.example.plugins.api

import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteUserService
import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.usecase.SignUpUseCase
import cherhy.example.plugins.util.constant.EndPoint.DELETE_USER
import cherhy.example.plugins.util.constant.EndPoint.GET_ME
import cherhy.example.plugins.util.constant.EndPoint.SIGN_UP
import cherhy.example.plugins.util.constant.EndPoint.UPDATE_USER
import cherhy.example.plugins.util.extension.accessToken
import cherhy.example.plugins.util.extension.refreshToken
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.user() {
    val signUpUseCase: SignUpUseCase by inject()
    val loginUseCase: LoginUseCase by inject()
    val readUserService: ReadUserService by inject()
    val writeUserService: WriteUserService by inject()

    post(SIGN_UP) {
        val userRequest = call.receive<SignUpRequest>()
        signUpUseCase.execute(userRequest)

        val loginRequest = LoginRequest.of(userRequest.email, userRequest.password)
        val jwt = loginUseCase.execute(loginRequest)

        call.response.headers.accessToken = jwt.accessToken
        call.response.cookies.refreshToken = jwt.refreshToken
        call.respond(HttpStatusCode.Created)
    }

    get(GET_ME) {
        call.respondText("health check")
    }

    put(UPDATE_USER) {
        call.respondText("health check")
    }

    delete(DELETE_USER) {
        call.respondText("health check")
    }
}
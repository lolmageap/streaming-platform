package cherhy.example.plugins.api

import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteUserService
import cherhy.example.plugins.usecase.SignUpUseCase
import cherhy.example.plugins.util.EndPoint.DELETE_USER
import cherhy.example.plugins.util.EndPoint.GET_ME
import cherhy.example.plugins.util.EndPoint.SIGN_UP
import cherhy.example.plugins.util.EndPoint.UPDATE_USER
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.user() {
    val signUpUseCase: SignUpUseCase by inject()
    val readUserService: ReadUserService by inject()
    val writeUserService: WriteUserService by inject()

    post(SIGN_UP) {
        call.respondText("health check")
        signUpUseCase.execute()
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
package cherhy.example.plugins.api

import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteUserService
import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.usecase.SignUpUseCase
import cherhy.example.plugins.util.extension.accessToken
import cherhy.example.plugins.util.extension.jwt
import cherhy.example.plugins.util.extension.refreshToken
import cherhy.example.plugins.util.extension.userId
import com.cherhy.common.util.User.GET_ME
import com.cherhy.common.util.User.SIGN_UP
import com.cherhy.common.util.User.UPDATE_USER
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
        val userId = call.jwt.userId
        val user = readUserService.get(userId)
        call.respond(HttpStatusCode.OK, user)
    }

    put(UPDATE_USER) {
        val userId = call.jwt.userId
        val request = call.receive<UserUpdateRequest>()
        val updatedUser = writeUserService.update(userId, request.email, request.password)
        call.respond(HttpStatusCode.OK, updatedUser.id.value)
    }
}
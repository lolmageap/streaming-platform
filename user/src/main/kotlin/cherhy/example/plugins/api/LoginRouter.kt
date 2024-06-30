package cherhy.example.plugins.api

import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.usecase.RefreshTokenUseCase
import cherhy.example.plugins.util.extension.accessToken
import cherhy.example.plugins.util.extension.refreshToken
import com.cherhy.common.util.User.LOGIN
import com.cherhy.common.util.User.LOGOUT
import com.cherhy.common.util.User.REFRESH
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.login() {
    val loginUseCase: LoginUseCase by inject()
    val refreshTokenUseCase: RefreshTokenUseCase by inject()

    post(LOGIN) {
        val loginRequest = call.receive<LoginRequest>()
        val jwt = loginUseCase.execute(loginRequest)

        call.response.headers.accessToken = jwt.accessToken
        call.response.cookies.refreshToken = jwt.refreshToken
        call.respond(HttpStatusCode.Created)
    }

    delete(LOGOUT) {
        call.response.headers.accessToken = null
        call.response.cookies.refreshToken = null
        call.respond(HttpStatusCode.OK)
    }

    // TODO: 현재 refresh token을 그대로 받아 decode하고 있는데 이 부분이 이미 gateway에서 처리되고 있음. userId를 받게 수정하자
    post(REFRESH) {
        val refreshToken = call.request.cookies.refreshToken
        val accessToken = refreshTokenUseCase.execute(refreshToken)

        call.response.headers.accessToken = accessToken
        call.respond(HttpStatusCode.Created)
    }
}
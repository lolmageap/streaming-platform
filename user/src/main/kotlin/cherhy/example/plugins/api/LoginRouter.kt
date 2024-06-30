package cherhy.example.plugins.api

import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.usecase.LoginUseCase
import cherhy.example.plugins.usecase.RefreshTokenUseCase
import cherhy.example.plugins.util.extension.accessToken
import cherhy.example.plugins.util.extension.refreshToken
import cherhy.example.plugins.util.extension.toUserId
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

    post(REFRESH) {
        val userId = call.receive<Long>().toUserId()
        val accessToken = refreshTokenUseCase.execute(userId)

        call.response.headers.accessToken = accessToken
        call.respond(HttpStatusCode.Created)
    }
}
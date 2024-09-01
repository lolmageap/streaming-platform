package cherhy.example.usecase

import cherhy.example.api.JwtResponse
import cherhy.example.api.LoginRequest
import cherhy.example.plugins.reactiveTransaction
import cherhy.example.service.ReadAuthorityService
import cherhy.example.service.ReadUserService
import cherhy.example.util.Encoder
import cherhy.example.util.JwtManager
import cherhy.example.util.TokenType.ACCESS
import cherhy.example.util.TokenType.REFRESH
import cherhy.example.util.TransactionType.READ_ONLY

class LoginUseCase(
    private val readUserService: ReadUserService,
    private val readAuthorityService: ReadAuthorityService,
    private val jwtManager: JwtManager,
) {
    suspend fun execute(
        loginRequest: LoginRequest,
    ) = reactiveTransaction(READ_ONLY) {
        val user = readUserService.get(loginRequest.email)
        val password = loginRequest.password.value
        val salt = user.salt.value
        val encodedPassword = user.password.value
        Encoder.ifMatches(password + salt, encodedPassword)

        val roles = readAuthorityService.get(user.id)
        val accessToken = jwtManager.createToken(user.id, user.name, roles, ACCESS)
        val refreshToken = jwtManager.createToken(user.id, user.name, roles, REFRESH)
        JwtResponse.of(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}
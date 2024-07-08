package cherhy.example.plugins.usecase

import cherhy.example.plugins.api.JwtResponse
import cherhy.example.plugins.api.LoginRequest
import cherhy.example.plugins.service.ReadAuthorityService
import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.util.Encoder
import cherhy.example.plugins.util.JwtManager
import cherhy.example.plugins.util.TokenType.ACCESS
import cherhy.example.plugins.util.TokenType.REFRESH

class LoginUseCase(
    private val readUserService: ReadUserService,
    private val readAuthorityService: ReadAuthorityService,
    private val jwtManager: JwtManager,
) {
    suspend fun execute(
        loginRequest: LoginRequest,
    ): JwtResponse {
        val user = readUserService.get(loginRequest.email)
        val password = loginRequest.password.value
        val salt = user.salt.value
        val encodedPassword = user.password.value
        Encoder.matchesBCrypt(password + salt, encodedPassword)

        val roles = readAuthorityService.get(user.id)
        val accessToken = jwtManager.createToken(user.id, user.name, roles, ACCESS)
        val refreshToken = jwtManager.createToken(user.id, user.name, roles, REFRESH)
        return JwtResponse.of(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}
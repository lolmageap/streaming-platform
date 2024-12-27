package cherhy.example.usecase

import cherhy.example.api.JwtResponse
import cherhy.example.domain.UserEmail
import cherhy.example.domain.UserPassword
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
        command: LoginCommand,
    ) = reactiveTransaction(READ_ONLY) {
        val user = readUserService.get(command.email)
        val password = command.password.value
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

data class LoginCommand(
    val email: UserEmail,
    val password: UserPassword,
) {
    companion object {
        @JvmStatic
        fun of(
            email: UserEmail,
            password: UserPassword,
        ) = LoginCommand(
            email = email,
            password = password,
        )
    }
}
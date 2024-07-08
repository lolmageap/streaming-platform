package cherhy.example.plugins.usecase

import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.service.ReadAuthorityService
import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.util.JwtManager
import cherhy.example.plugins.util.TokenType.ACCESS

class RefreshTokenUseCase(
    private val jwtManager: JwtManager,
    private val readUserService: ReadUserService,
    private val readAuthorityService: ReadAuthorityService,
) {
    suspend fun execute(
        userId: UserId,
    ): String {
        val user = readUserService.get(userId)
        val roles = readAuthorityService.get(user.id)
        return jwtManager.createToken(user.id, user.name, roles, ACCESS)
    }
}
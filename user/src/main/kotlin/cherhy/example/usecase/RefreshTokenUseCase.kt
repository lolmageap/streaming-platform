package cherhy.example.usecase

import cherhy.example.plugins.reactiveTransaction
import cherhy.example.service.ReadAuthorityService
import cherhy.example.service.ReadUserService
import cherhy.example.util.JwtManager
import cherhy.example.util.TokenType.ACCESS
import com.cherhy.common.util.model.UserId

class RefreshTokenUseCase(
    private val jwtManager: JwtManager,
    private val readUserService: ReadUserService,
    private val readAuthorityService: ReadAuthorityService,
) {
    suspend fun execute(
        userId: UserId,
    ) = reactiveTransaction {
        val user = readUserService.get(userId)
        val roles = readAuthorityService.get(user.id)
        jwtManager.createToken(user.id, user.name, roles, ACCESS)
    }
}
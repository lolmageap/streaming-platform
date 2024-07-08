package cherhy.example.plugins.service

import cherhy.example.plugins.domain.Role
import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.repository.AuthorityRepository

class ReadAuthorityService(
    private val authorityRepository: AuthorityRepository,
) {
    suspend fun get(userId: UserId) =
        authorityRepository.findByUserId(userId)
            .map { Role.valueOf(it.role) }
}
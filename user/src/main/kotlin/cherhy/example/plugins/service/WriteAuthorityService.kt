package cherhy.example.plugins.service

import cherhy.example.plugins.domain.Role
import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.repository.AuthorityRepository

class WriteAuthorityService(
    private val authorityRepository: AuthorityRepository,
) {
    suspend fun createAuthority(
        id: UserId,
        role: Role,
    ) {
        authorityRepository.save(id, role)
    }
}
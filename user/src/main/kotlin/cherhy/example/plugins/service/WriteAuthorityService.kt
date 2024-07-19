package cherhy.example.plugins.service

import cherhy.example.plugins.domain.Role
import cherhy.example.plugins.repository.AuthorityRepository
import com.cherhy.common.util.model.UserId

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
package cherhy.example.service

import cherhy.example.domain.Role
import cherhy.example.repository.AuthorityRepository
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
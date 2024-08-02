package cherhy.example.service

import cherhy.example.domain.Role
import cherhy.example.repository.AuthorityRepository
import com.cherhy.common.util.model.UserId

class ReadAuthorityService(
    private val authorityRepository: AuthorityRepository,
) {
    suspend fun get(
        userId: UserId,
    ) =
        authorityRepository.findByUserId(userId)
            .map { Role.valueOf(it.role) }
}
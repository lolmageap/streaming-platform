package cherhy.example.plugins.repository

import cherhy.example.plugins.domain.*
import org.jetbrains.exposed.dao.id.EntityID

interface AuthorityRepository {
    suspend fun save(
        id: UserId,
        role: Role,
    ): Authority

    suspend fun findByUserId(
        userId: UserId,
    ): List<Authority>
}

class AuthorityRepositoryImpl: AuthorityRepository {
    override suspend fun save(
        id: UserId,
        role: Role,
    ) =
        Authority.new {
            this.userId = EntityID(id.value, Users)
            this.role = role.name
        }

    override suspend fun findByUserId(
        userId: UserId,
    ) =
        Authority.find { Authorities.userId eq userId.value }.toList()
}
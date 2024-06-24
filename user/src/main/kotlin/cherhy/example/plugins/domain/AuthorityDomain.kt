package cherhy.example.plugins.domain

data class AuthorityDomain(
    val id: AuthorityId,
    val userId: UserId,
    val role: Role,
) {
    companion object {
        @JvmStatic
        fun of(
            authority: Authority,
        ) = with(authority) {
            AuthorityDomain(
                id = AuthorityId.of(id.value),
                userId = UserId.of(userId.value),
                role = Role.valueOf(role),
            )
        }
    }
}

@JvmInline
value class AuthorityId private constructor(val value: Long): Comparable<AuthorityId> {
    override fun compareTo(other: AuthorityId): Int {
        return value.compareTo(other.value)
    }
    companion object {
        @JvmStatic
        fun of(value: Long) = AuthorityId(value)
    }
}

enum class Role {
    ADMIN,
    PAID_MEMBER,
    UNPAID_MEMBER,
    ;
}
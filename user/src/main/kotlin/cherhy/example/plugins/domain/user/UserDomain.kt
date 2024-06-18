package cherhy.example.plugins.domain.user

data class UserDomain(
    val id: UserId,
    val name: UserName,
    val email: UserEmail,
    val password: UserPassword,
)

@JvmInline
value class UserId(val value: Long): Comparable<UserId> {
    override fun compareTo(other: UserId): Int {
        return value.compareTo(other.value)
    }
    companion object {
        fun of(value: Long) = UserId(value)
    }
}

@JvmInline
value class UserName(val value: String) {
    companion object {
        fun of(value: String) = UserName(value)
    }
}

@JvmInline
value class UserEmail(val value: String) {
    companion object {
        fun of(value: String) = UserEmail(value)
    }
}

@JvmInline
value class UserPassword(val value: String) {
    companion object {
        fun of(value: String) = UserPassword(value)
    }
}
package cherhy.example.plugins.domain

data class UserDomain(
    val id: UserId,
    val name: Username,
    val email: UserEmail,
    val password: UserPassword,
    var isDeleted: UserIsDeleted = UserIsDeleted.of(false),
)

@JvmInline
value class UserId private constructor(val value: Long): Comparable<UserId> {
    override fun compareTo(other: UserId): Int {
        return value.compareTo(other.value)
    }
    companion object {
        @JvmStatic
        fun of(value: Long) = UserId(value)
    }
}

@JvmInline
value class Username private constructor(val value: String) {
    companion object {
        @JvmStatic
        fun of(value: String) = Username(value)
    }
}

@JvmInline
value class UserEmail private constructor(val value: String) {
    companion object {
        @JvmStatic
        fun of(value: String) = UserEmail(value)
    }
}

@JvmInline
value class UserPassword private constructor(val value: String) {
    companion object {
        @JvmStatic
        fun of(value: String) = UserPassword(value)
    }
}

@JvmInline
value class UserIsDeleted private constructor(val value: Boolean) {
    companion object {
        @JvmStatic
        fun of(value: Boolean) = UserIsDeleted(value)
    }
}
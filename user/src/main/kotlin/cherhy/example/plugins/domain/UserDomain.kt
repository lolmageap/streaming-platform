package cherhy.example.plugins.domain

data class UserDomain(
    val id: UserId,
    val name: Username,
    val email: UserEmail,
    val password: UserPassword,
    val salt: UserSalt,
    var isDeleted: UserIsDeleted = UserIsDeleted.of(false),
) {
    companion object {
        @JvmStatic
        fun of(
            user: User,
        ) = UserDomain(
            id = UserId.of(user.id.value),
            name = Username.of(user.name),
            email = UserEmail.of(user.email),
            password = UserPassword.of(user.password),
            salt = UserSalt.of(user.salt),
            isDeleted = UserIsDeleted.of(user.isDeleted),
        )
    }
}

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
value class UserSalt private constructor(val value: String) {
    companion object {
        @JvmStatic
        fun of(value: String) = UserSalt(value)
    }
}

@JvmInline
value class UserIsDeleted private constructor(val value: Boolean) {
    companion object {
        @JvmStatic
        fun of(value: Boolean) = UserIsDeleted(value)
    }
}
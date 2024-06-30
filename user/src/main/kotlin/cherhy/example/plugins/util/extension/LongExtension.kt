package cherhy.example.plugins.util.extension

import cherhy.example.plugins.domain.UserId

fun Long.toUserId() = UserId.of(this)
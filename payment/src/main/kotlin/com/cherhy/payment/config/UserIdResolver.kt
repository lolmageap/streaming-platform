package com.cherhy.payment.config

import com.cherhy.common.util.model.UserId

interface UserIdResolver {
    fun resolve(): UserId
}
package com.cherhy.common.config

interface UserIdResolver {
    fun resolve(): Long
}
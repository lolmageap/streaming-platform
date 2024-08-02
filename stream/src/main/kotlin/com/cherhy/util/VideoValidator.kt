package com.cherhy.util

object VideoValidator {
    fun validate(
        name: String,
        size: Long,
    ) {
        require(name.isNotBlank()) { "Name must not be blank" }
        val isVideo = name.endsWith(".mp4")
        require(isVideo) { "Invalid video format." }

        require(size > 0) { "Size must be greater than 0" }
        require(size <= 1024 * 1024 * 1024) { "Size must be less than 1GB" }
    }
}
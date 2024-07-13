package com.cherhy.plugins.api

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.domain.*

data class VideoDetailResponse private constructor(
    val id: VideoId,
    var name: VideoName,
    var uniqueName: VideoUniqueName,
    var size: VideoSize,
    var extension: VideoExtension,
    val owner: UserId,
    val post: PostId,
) {
    companion object {
        fun of(
            video: Video,
        ) = with(video) {
            VideoDetailResponse(
                id,
                name,
                uniqueName,
                size,
                extension,
                owner,
                post,
            )
        }
    }
}
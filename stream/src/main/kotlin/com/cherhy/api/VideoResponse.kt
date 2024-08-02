package com.cherhy.api

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.*

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

data class GetVideoResponse(
    val videoStream: ByteArray,
    val totalSize: VideoSize,
    val currentSize: VideoSize,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GetVideoResponse

        if (!videoStream.contentEquals(other.videoStream)) return false
        if (totalSize != other.totalSize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = videoStream.contentHashCode()
        result = 31 * result + totalSize.hashCode()
        return result
    }

    companion object {
        @JvmStatic
        fun of(
            videoStream: ByteArray,
            videoTotalSize: VideoSize,
        ) = GetVideoResponse(
            videoStream,
            videoTotalSize,
            VideoSize.of(videoStream.size.toLong()),
        )
    }
}
package com.cherhy.usecase

import com.cherhy.service.ReadVideoService

class BuyVideoUseCase(
    private val readVideoService: ReadVideoService,
) {
    suspend fun execute(
        userId: Long,
        videoId: Long,
        price: Long,
    ) {
        TODO("회원이 비디오 구매했는지에 대한 entity도 생성해야함. -> 이미 결제한 비디오인지 확인하는 로직도 필요함.")
    }
}
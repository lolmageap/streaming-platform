package com.cherhy.payment.adapter.out

import com.cherhy.payment.exception.RedisLockException
import kotlinx.coroutines.reactor.awaitSingle
import org.redisson.api.RedissonReactiveClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration

@Component
class DistributedLock(
    private val redisson: RedissonReactiveClient,
) {
    suspend fun <T> redLock(
        key: String,
        waitTime: Duration = 5000.milliseconds,
        leaseTime: Duration = 1000.milliseconds,
        block: () -> T,
    ): T {
        val lock = redisson.getLock(key)
        val waitTimeToLong = waitTime.toJavaDuration().toMillis()
        val leaseTimeToLong = leaseTime.toJavaDuration().toMillis()

        return try {
            val hasLock = lock.tryLock(waitTimeToLong, leaseTimeToLong, TimeUnit.MILLISECONDS).awaitSingle()

            if (hasLock) block.invoke()
            else throw RedisLockException()
        } finally {
            lock.unlock().awaitSingle()
        }
    }
}

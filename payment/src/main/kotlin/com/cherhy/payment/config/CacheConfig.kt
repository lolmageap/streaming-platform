package com.cherhy.payment.config

import com.cherhy.payment.util.property.CacheProperty
import mu.KotlinLogging
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer

@EnableCaching
@Configuration
class CacheConfig(
    private val cacheProperty: CacheProperty,
) {
    @Bean
    fun redisConnectionFactory() =
        LettuceConnectionFactory(
            RedisStandaloneConfiguration(cacheProperty.host, cacheProperty.port)
        )

    @Bean
    fun localCacheManager(): CacheManager {
        return object : CacheManager {
            private val logger = KotlinLogging.logger {}

            val cacheConfiguration =
                RedisCacheConfiguration
                    .defaultCacheConfig()
                    .serializeValuesWith(
                        fromSerializer(
                            Jackson2JsonRedisSerializer(Any::class.java)
                        )
                    )

            private val delegate =
                RedisCacheManager
                    .builder(redisConnectionFactory())
                    .cacheDefaults(cacheConfiguration)
                    .enableCreateOnMissingCache()
                    .build()

            override fun getCache(
                name: String,
            ): Cache? =
                delegate.getCache(name).also {
                    if (it != null) logger.info { "Cache Hit : $name" }
                    else logger.info { "Cache Miss : $name" }
                }

            override fun getCacheNames() =
                delegate.cacheNames
        }
    }
}
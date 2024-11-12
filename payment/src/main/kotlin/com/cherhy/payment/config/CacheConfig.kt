package com.cherhy.payment.config

import com.cherhy.payment.util.property.CacheProperty
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
class CacheConfig(
    private val cacheProperty: CacheProperty,
) {
    @Bean
    fun stringRedisTemplate() =
        StringRedisTemplate(redisConnectionFactory())
            .apply {
                valueSerializer = GenericJackson2JsonRedisSerializer()
            }

    @Bean
    fun redisConnectionFactory() =
        LettuceConnectionFactory(
            RedisStandaloneConfiguration(cacheProperty.host, cacheProperty.port)
        )

    @Bean
    fun redisCacheManager() =
        RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())
            .cacheDefaults(defaultConfiguration())
            .build()

    private fun defaultConfiguration() =
        RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer()
                )
            )
            .entryTtl(Duration.ofMinutes(10))
}
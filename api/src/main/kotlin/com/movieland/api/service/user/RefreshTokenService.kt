package com.movieland.api.service.user

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val redisTemplate: RedisTemplate<String, String>
) {

    private val opsForHash = redisTemplate.opsForHash<String, String>()

    fun setUserRefreshToken(userId: String, refreshToken: String) {
        opsForHash.put(rootUserKey(), userId, refreshToken)
    }

    private fun rootUserKey(): String {
        return "refresh-token:user:v1"
    }
}

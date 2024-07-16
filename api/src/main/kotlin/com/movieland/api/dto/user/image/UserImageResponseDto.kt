package com.movieland.api.dto.user.image

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.image.UserImageType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserImageResponseDto(
    val id: Long,
    val originUrl: String,
    val name: String,
    val type: UserImageType,
    val createdAt: Long,
    val updatedAt: Long
)

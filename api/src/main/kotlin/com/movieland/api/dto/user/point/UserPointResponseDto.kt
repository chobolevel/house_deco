package com.movieland.api.dto.user.point

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.point.UserPointType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserPointResponseDto(
    val id: String,
    val currency: Int,
    val type: UserPointType,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long
)

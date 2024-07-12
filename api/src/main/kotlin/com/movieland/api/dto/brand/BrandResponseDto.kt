package com.movieland.api.dto.brand

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BrandResponseDto(
    val id: String,
    val name: String,
    val link: String,
    val createdAt: Long,
    val updatedAt: Long,
)

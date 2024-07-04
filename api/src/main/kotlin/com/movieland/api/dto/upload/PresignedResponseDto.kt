package com.movieland.api.dto.upload

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PresignedResponseDto(
    val presignedUrl: String,
    val originUrl: String,
    val filename: String
)

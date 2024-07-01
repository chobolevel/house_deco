package com.movieland.api.dto.upload

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreatePresignedUrlRequestDto(
    val prefix: String,
    val filename: String
)

package com.movieland.api.dto.user.image

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserProfileImageRequestDto(
    @field:NotBlank(message = "origin_url is required")
    val originUrl: String,
    @field:NotBlank(message = "name is required")
    val name: String,
)

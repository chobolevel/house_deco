package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ReissueRequestDto(
    @field:NotBlank(message = "리프래시 토큰은 필수 값입니다.")
    val refreshToken: String
)

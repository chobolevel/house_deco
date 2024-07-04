package com.movieland.api.dto.user.image

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.image.UserImageUpdateMask
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateUserProfileImageRequestDto(
    val originUrl: String?,
    val name: String?,
    @field:NotBlank(message = "update_mask is required")
    val updateMask: List<UserImageUpdateMask>
)

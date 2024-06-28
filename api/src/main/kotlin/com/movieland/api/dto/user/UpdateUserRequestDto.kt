package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.UserUpdateMaskType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateUserRequestDto(
    val nickname: String?,
    val phone: String?,
    val updateMask: List<UserUpdateMaskType>
)

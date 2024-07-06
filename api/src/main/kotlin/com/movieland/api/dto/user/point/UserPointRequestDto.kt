package com.movieland.api.dto.user.point

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.point.UserPointType
import com.movieland.domain.entity.user.point.UserPointUpdateMask
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserPointRequestDto(
    @field:NotNull(message = "부여할 포인트는 필수 값입니다.")
    val currency: Int,
    @field:NotNull(message = "부여할 포인트 타입은 필수 값입니다.")
    val type: UserPointType,
    @field:NotBlank(message = "부여할 포인트 상세는 필수 값입니다.")
    val description: String
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UpdateUserPointRequestDto(
    val currency: Int?,
    val type: UserPointType?,
    val description: String?,
    val updateMask: List<UserPointUpdateMask>
)

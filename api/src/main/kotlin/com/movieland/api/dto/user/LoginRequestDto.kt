package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.UserLoginType
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LoginRequestDto(
    val email: String?,
    val password: String?,
    val socialId: String?,
    @field:NotNull(message = "로그인 타입은 필수 값입니다.")
    val loginType: UserLoginType
)

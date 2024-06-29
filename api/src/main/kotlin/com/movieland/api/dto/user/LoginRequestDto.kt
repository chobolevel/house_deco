package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.UserLoginType

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class LoginRequestDto(
    val email: String?,
    val password: String?,
    val socialId: String?,
    val loginType: UserLoginType
)

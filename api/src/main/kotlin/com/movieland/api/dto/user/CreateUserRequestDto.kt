package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserRequestDto(
    @field:NotBlank(message = "이메일은 필수 값입니다.")
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수 값입니다.")
    val password: String,
    val socialId: String?,
    @field:NotBlank(message = "닉네임은 필수 값입니다.")
    val nickname: String,
    @field:NotBlank(message = "전화번호는 필수 값입니다.")
    @field:Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10-11자리 숫자여야 합니다.")
    val phone: String,
)

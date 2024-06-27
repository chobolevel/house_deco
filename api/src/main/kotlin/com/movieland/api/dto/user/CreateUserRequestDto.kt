package com.movieland.api.dto.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.movieland.domain.entity.user.UserLoginType
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserRequestDto(
  @field:NotBlank(message = "아이디는 필수 값입니다.")
  val username: String,
  @field:NotBlank(message = "비밀번호는 필수 값입니다.")
  val password: String,
  val socialId: String?,
  @field:NotBlank(message = "회원 타입은 필수 값입니다.")
  val loginType: UserLoginType,
  @field:NotBlank(message = "닉네임은 필수 값입니다.")
  val nickname: String,
  @field:NotBlank(message = "전화번호는 필수 값입니다.")
  val phone: String,
)

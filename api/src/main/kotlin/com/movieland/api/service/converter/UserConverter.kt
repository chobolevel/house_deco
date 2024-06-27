package com.movieland.api.service.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.domain.entity.user.User
import com.movieland.domain.entity.user.UserRoleType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserConverter(
  private val passwordEncoder: BCryptPasswordEncoder,
  private val tsidFactory: TsidFactory
) {

  fun convert(request: CreateUserRequestDto): User {
    return User(
      id = tsidFactory.create().toString(),
      username = request.username,
      password = passwordEncoder.encode(request.password),
      socialId = request.socialId,
      loginType = request.loginType,
      nickname = request.nickname,
      phone = request.phone,
      role = UserRoleType.ROLE_USER
    )
  }
}

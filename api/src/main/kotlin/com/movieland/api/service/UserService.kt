package com.movieland.api.service

import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
  private val userRepository: UserRepository,
  private val userFinder: UserFinder
) {

  fun create(request: CreateUserRequestDto): String {
    return ""
  }

}

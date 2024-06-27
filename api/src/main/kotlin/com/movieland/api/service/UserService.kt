package com.movieland.api.service

import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.api.service.converter.UserConverter
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
  private val userRepository: UserRepository,
  private val userFinder: UserFinder,
  private val userConverter: UserConverter
) {

  fun create(request: CreateUserRequestDto): String {
    // TODO 레코드 중복 검증

    val user = userConverter.convert(request)
    return userRepository.save(user).id!!
  }
}

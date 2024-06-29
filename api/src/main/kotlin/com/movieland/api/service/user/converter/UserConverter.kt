package com.movieland.api.service.user.converter

import com.github.f4b6a3.tsid.TsidFactory
import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.api.dto.user.UserResponseDto
import com.movieland.domain.entity.user.User
import com.movieland.domain.entity.user.UserRoleType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.ZoneOffset

@Component
class UserConverter(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val tsidFactory: TsidFactory
) {

    fun convert(request: CreateUserRequestDto): User {
        return User(
            id = tsidFactory.create().toString(),
            email = request.email,
            password = passwordEncoder.encode(request.password),
            socialId = request.socialId,
            loginType = request.loginType,
            nickname = request.nickname,
            phone = request.phone,
            role = UserRoleType.ROLE_USER
        )
    }

    fun convert(user: User): UserResponseDto {
        return UserResponseDto(
            id = user.id!!,
            email = user.email!!,
            loginType = user.loginType!!,
            nickname = user.nickname!!,
            phone = user.phone!!,
            role = user.role,
            createdAt = user.createdAt!!.toInstant(ZoneOffset.UTC).toEpochMilli(),
            updatedAt = user.updatedAt!!.toInstant(ZoneOffset.UTC).toEpochMilli()
        )
    }
}

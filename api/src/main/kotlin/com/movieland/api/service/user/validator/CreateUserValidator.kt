package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class CreateUserValidator(
    private val userFinder: UserFinder
) : CreateUserValidatable {
    override fun validate(request: CreateUserRequestDto) {
        val emailExists = userFinder.existsByEmail(request.email)
        val nicknameExists = userFinder.existsByNickname(request.nickname)
        val phoneExists = userFinder.existsByPhone(request.phone)
        if (emailExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.EMAIL_ALREADY_EXISTS,
                message = "이미 존재하는 이메일입니다."
            )
        }
        if (nicknameExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.NICKNAME_ALREADY_EXISTS,
                message = "이미 존재하는 닉네임입니다."
            )
        }
        if (phoneExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.PHONE_ALREADY_EXISTS,
                message = "이미 존재하는 전화번호입니다."
            )
        }
    }
}

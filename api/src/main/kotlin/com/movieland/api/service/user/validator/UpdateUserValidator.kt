package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.domain.entity.user.UserUpdateMaskType
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class UpdateUserValidator : UpdateUserValidatable {

    override fun validate(request: UpdateUserRequestDto) {
        request.updateMask.forEach {
            when (it) {
                UserUpdateMaskType.NICKNAME -> {
                    if (request.nickname.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 닉네임이 유효하지 않습니다."
                        )
                    }
                }

                UserUpdateMaskType.PHONE -> {
                    val regexp = "^\\d{10,11}$".toRegex()
                    if (request.phone.isNullOrEmpty() || regexp.matches(request.phone)) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 전화번호가 유효하지 않거나 형식이 올바르지 않습니다."
                        )
                    }
                }
            }
        }
    }
}

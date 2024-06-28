package com.movieland.api.service.updater

import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.domain.entity.user.User
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserUpdateMaskType
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class UserUpdater(
    private val userFinder: UserFinder
) : UserUpdatable {

    override fun markAsUpdate(request: UpdateUserRequestDto, user: User): User {
        // 본인 닉네임/전화번호 경우 처리 필요
        request.updateMask.forEach {
            when (it) {
                UserUpdateMaskType.NICKNAME -> {
                    if (userFinder.existsByNickname(request.nickname!!)) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "이미 존재하는 닉네임입니다."
                        )
                    }
                    user.nickname = request.nickname
                }

                UserUpdateMaskType.PHONE -> {
                    if (userFinder.existsByPhone(request.phone!!)) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "이미 존재하는 휴대폰 번호입니다."
                        )
                    }
                    user.phone = request.phone
                }
            }
        }
        return user
    }

    override fun order(): Int {
        return 0
    }
}

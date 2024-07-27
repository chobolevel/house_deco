package com.movieland.api.service.user.validator

import com.movieland.api.dto.user.point.UpdateUserPointRequestDto
import com.movieland.domain.entity.user.point.UserPointUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class UpdateUserPointValidator : UpdateUserPointValidatable {

    override fun validate(request: UpdateUserPointRequestDto) {
        request.updateMask.forEach {
            when (it) {
                UserPointUpdateMask.CURRENCY -> {
                    if (request.currency == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 포인트가 유효하지 않습니다."
                        )
                    }
                }

                UserPointUpdateMask.TYPE -> {
                    if (request.type == null) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 포인트 타입이 유효하지 않습니다."
                        )
                    }
                }

                UserPointUpdateMask.DESCRIPTION -> {
                    if (request.description.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_PARAMETER,
                            message = "변경할 포인트 상세가 유효하지 않습니다."
                        )
                    }
                }
            }
        }
    }
}

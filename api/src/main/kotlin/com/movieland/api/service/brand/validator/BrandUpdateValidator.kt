package com.movieland.api.service.brand.validator

import com.movieland.api.dto.brand.UpdateBrandRequestDto
import com.movieland.domain.entity.brand.BrandUpdateMask
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.stereotype.Component

@Component
class BrandUpdateValidator : BrandUpdateValidatable {

    override fun validate(request: UpdateBrandRequestDto) {
        request.updateMask.forEach {
            when (it) {
                BrandUpdateMask.NAME -> {
                    if (request.name.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_BRAND_NAME,
                            message = "잘못된 브랜드명입니다."
                        )
                    }
                }

                BrandUpdateMask.LINK -> {
                    if (request.link.isNullOrEmpty()) {
                        throw ParameterInvalidException(
                            errorCode = ErrorCode.INVALID_BRAND_LINK,
                            message = "잘못된 브랜드 링크입니다."
                        )
                    }
                }
            }
        }
    }
}

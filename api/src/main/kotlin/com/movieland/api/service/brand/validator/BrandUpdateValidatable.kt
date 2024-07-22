package com.movieland.api.service.brand.validator

import com.movieland.api.dto.brand.UpdateBrandRequestDto

interface BrandUpdateValidatable {

    fun validate(request: UpdateBrandRequestDto)
}

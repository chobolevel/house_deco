package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.UpdateProductRequestDto

interface ProductUpdateValidatable {

    fun validate(request: UpdateProductRequestDto)
}

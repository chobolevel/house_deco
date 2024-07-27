package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.CreateProductRequestDto

interface CreateProductValidatable {

    fun validate(request: CreateProductRequestDto)
}

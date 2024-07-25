package com.movieland.api.service.product.validator.option

import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto

interface ProductOptionUpdateValidatable {

    fun validate(request: UpdateProductOptionRequestDto)
}

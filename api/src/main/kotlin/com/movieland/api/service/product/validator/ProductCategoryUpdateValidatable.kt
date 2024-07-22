package com.movieland.api.service.product.validator

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto

interface ProductCategoryUpdateValidatable {

    fun validate(request: UpdateProductCategoryRequestDto)
}

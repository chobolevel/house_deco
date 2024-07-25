package com.movieland.api.service.product.validator.category

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto

interface ProductCategoryUpdateValidatable {

    fun validate(request: UpdateProductCategoryRequestDto)
}

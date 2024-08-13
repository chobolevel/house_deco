package com.movieland.api.service.product.updater.option

import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto
import com.movieland.domain.entity.product.option.ProductOption

interface ProductOptionUpdatable {

    fun markAsUpdate(request: UpdateProductOptionRequestDto, productOption: ProductOption): ProductOption

    fun order(): Int
}

package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.domain.entity.product.image.ProductImage

interface ProductImageUpdatable {

    fun markAsUpdate(request: UpdateProductImageRequestDto, productImage: ProductImage): ProductImage

    fun order(): Int
}

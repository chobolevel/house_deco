package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.domain.entity.product.Product

interface ProductUpdatable {

    fun markAsUpdate(request: UpdateProductRequestDto, product: Product): Product

    fun order(): Int
}

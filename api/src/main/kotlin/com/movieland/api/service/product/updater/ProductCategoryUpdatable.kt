package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.domain.entity.product.category.ProductCategory

interface ProductCategoryUpdatable {

    fun markAsUpdate(request: UpdateProductCategoryRequestDto, productCategory: ProductCategory): ProductCategory

    fun order(): Int
}

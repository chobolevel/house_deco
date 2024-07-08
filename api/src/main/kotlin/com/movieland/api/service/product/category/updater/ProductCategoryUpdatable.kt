package com.movieland.api.service.product.category.updater

import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.domain.entity.product.category.ProductCategory

interface ProductCategoryUpdatable {

    fun markAsUpdate(request: UpdateProductCategoryRequestDto, productCategory: ProductCategory): ProductCategory

    fun order(): Int
}

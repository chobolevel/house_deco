package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.domain.entity.product.image.ProductImage
import com.movieland.domain.entity.product.image.ProductImageUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductImageUpdater : ProductImageUpdatable {

    override fun markAsUpdate(request: UpdateProductImageRequestDto, productImage: ProductImage): ProductImage {
        request.updateMask.forEach {
            when (it) {
                ProductImageUpdateMask.ORIGIN_URL -> productImage.originUrl = request.originUrl!!
                ProductImageUpdateMask.NAME -> productImage.name = request.name!!
                ProductImageUpdateMask.TYPE -> productImage.type = request.type!!
            }
        }
        return productImage
    }

    override fun order(): Int {
        return 0
    }
}

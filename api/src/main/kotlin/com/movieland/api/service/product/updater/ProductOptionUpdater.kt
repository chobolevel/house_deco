package com.movieland.api.service.product.updater

import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto
import com.movieland.domain.entity.product.option.ProductOption
import com.movieland.domain.entity.product.option.ProductOptionUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductOptionUpdater : ProductOptionUpdatable {

    override fun markAsUpdate(request: UpdateProductOptionRequestDto, productOption: ProductOption): ProductOption {
        request.updateMask.forEach {
            when (it) {
                ProductOptionUpdateMask.NAME -> productOption.name = request.name!!
                ProductOptionUpdateMask.ORIGINAL_PRICE -> productOption.originalPrice = request.originalPrice!!
                ProductOptionUpdateMask.SALE_PRICE -> productOption.salePrice = request.salePrice!!
                ProductOptionUpdateMask.STOCK -> productOption.stock = request.stock!!
                ProductOptionUpdateMask.ORDER -> productOption.order = request.order!!
            }
        }
        return productOption
    }

    override fun order(): Int {
        return 0
    }
}

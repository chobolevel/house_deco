package com.movieland.api.service.product.updater.review

import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto
import com.movieland.domain.entity.product.review.ProductReview
import com.movieland.domain.entity.product.review.ProductReviewUpdateMask
import org.springframework.stereotype.Component

@Component
class ProductReviewUpdater : ProductReviewUpdatable {

    override fun markAsUpdate(request: UpdateProductReviewRequestDto, productReview: ProductReview): ProductReview {
        request.updateMask.forEach {
            when (it) {
                ProductReviewUpdateMask.RATING -> productReview.rating = request.rating!!
                ProductReviewUpdateMask.COMMENT -> productReview.comment = request.comment!!
            }
        }
        return productReview
    }

    override fun order(): Int {
        return 0
    }
}

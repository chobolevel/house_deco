package com.movieland.api.service.product.updater.review

import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto
import com.movieland.domain.entity.product.review.ProductReview

interface ProductReviewUpdatable {

    fun markAsUpdate(request: UpdateProductReviewRequestDto, productReview: ProductReview): ProductReview

    fun order(): Int
}

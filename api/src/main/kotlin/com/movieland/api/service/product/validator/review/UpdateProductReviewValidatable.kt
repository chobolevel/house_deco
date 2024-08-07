package com.movieland.api.service.product.validator.review

import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto

interface UpdateProductReviewValidatable {

    fun validate(request: UpdateProductReviewRequestDto)
}

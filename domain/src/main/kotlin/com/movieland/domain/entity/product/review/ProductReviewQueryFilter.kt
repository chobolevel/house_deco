package com.movieland.domain.entity.product.review

import com.movieland.domain.entity.product.review.QProductReview.productReview
import com.querydsl.core.types.dsl.BooleanExpression

class ProductReviewQueryFilter(
    private val userId: Long?,
    private val productId: Long?
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            userId?.let { productReview.user.id.eq(it) },
            productId?.let { productReview.product.id.eq(it) }
        ).toTypedArray()
    }
}

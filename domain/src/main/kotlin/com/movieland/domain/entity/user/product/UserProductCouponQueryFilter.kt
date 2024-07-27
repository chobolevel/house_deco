package com.movieland.domain.entity.user.product

import com.movieland.domain.entity.user.product.QUserProductCoupon.userProductCoupon
import com.querydsl.core.types.dsl.BooleanExpression

class UserProductCouponQueryFilter(
    val userId: Long?
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            userId?.let { userProductCoupon.user.id.eq(it) }
        ).toTypedArray()
    }
}

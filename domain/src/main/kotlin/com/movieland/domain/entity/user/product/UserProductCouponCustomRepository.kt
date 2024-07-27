package com.movieland.domain.entity.user.product

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.product.QUserProductCoupon.userProductCoupon
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Component

@Component
class UserProductCouponCustomRepository : QuerydslRepositorySupport(UserProductCoupon::class.java) {

    fun searchByPredicates(
        predicates: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<UserProductCoupon> {
        return from(userProductCoupon)
            .where(userProductCoupon.used.not(), *predicates)
            .orderBy(*orderSpecifiers)
            .offset(pagination.skip)
            .limit(pagination.limit)
            .fetch()
    }

    fun countByPredicates(predicates: Array<BooleanExpression>): Long {
        return from(userProductCoupon)
            .where(userProductCoupon.used.not(), *predicates)
            .fetchCount()
    }
}

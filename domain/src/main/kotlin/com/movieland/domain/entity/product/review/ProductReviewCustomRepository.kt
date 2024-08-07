package com.movieland.domain.entity.product.review

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.review.QProductReview.productReview
import com.querydsl.core.Tuple
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import kotlinx.coroutines.selects.select
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductReviewCustomRepository : QuerydslRepositorySupport(ProductReview::class.java) {

    fun searchByPredicates(
        predicates: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<ProductReview> {
        return from(productReview)
            .where(*predicates)
            .orderBy(*orderSpecifiers)
            .offset(pagination.skip)
            .limit(pagination.limit)
            .fetch()
    }

    fun countByPredicates(predicates: Array<BooleanExpression>): Long {
        return from(productReview)
            .where(*predicates)
            .fetchCount()
    }

    fun countAndSumOfRatingByPredicates(predicates: Array<BooleanExpression>): Tuple {
        return from(productReview)
            .select(productReview.count(), productReview.rating.sum())
            .where(*predicates)
            .fetchOne()
    }
}

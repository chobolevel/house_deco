package com.movieland.domain.entity.product.review

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.review.QProductReview.productReview
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.Tuple
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class ProductReviewFinder(
    private val repository: ProductReviewRepository,
    private val customRepository: ProductReviewCustomRepository
) {

    @Throws(DataNotFoundException::class)
    fun findByIdAndUserId(id: Long, userId: Long): ProductReview {
        return repository.findByIdAndUserIdAndDeletedFalse(id, userId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_REVIEW_IS_NOT_FOUND,
            message = "상품 후기를 찾을 수 없습니다."
        )
    }

    fun existsByUserIdAndProductId(userId: Long, productId: Long): Boolean {
        return repository.existsByUserIdAndProductIdAndDeletedFalse(userId, productId)
    }

    fun search(
        queryFilter: ProductReviewQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductReviewOrderType>?
    ): List<ProductReview> {
        val orderSpecifiers = orderSpecifiers(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: ProductReviewQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    fun searchCountAndSumOfRating(queryFilter: ProductReviewQueryFilter): Tuple {
        return customRepository.countAndSumOfRatingByPredicates(queryFilter.toPredicates())
    }

    private fun orderSpecifiers(orderTypes: List<ProductReviewOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                ProductReviewOrderType.CREATED_AT_ASC -> productReview.createdAt.asc()
                ProductReviewOrderType.CREATED_AT_DESC -> productReview.createdAt.desc()
            }
        }.toTypedArray()
    }
}

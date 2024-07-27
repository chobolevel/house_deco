package com.movieland.domain.entity.user.product

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.product.QUserProductCoupon.userProductCoupon
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class UserProductCouponFinder(
    private val repository: UserProductCouponRepository,
    private val customRepository: UserProductCouponCustomRepository
) {

    fun existsByUserIdAndProductCouponId(userId: Long, productCouponId: Long): Boolean {
        return repository.existsByUserIdAndProductCouponId(userId, productCouponId)
    }

    fun search(
        queryFilter: UserProductCouponQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserProductCouponOrderType>?
    ): List<UserProductCoupon> {
        val orderSpecifiers = orderSpecifier(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: UserProductCouponQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    fun orderSpecifier(orderTypes: List<UserProductCouponOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                UserProductCouponOrderType.CREATED_AT_ASC -> userProductCoupon.createdAt.asc()
                UserProductCouponOrderType.CREATED_AT_DESC -> userProductCoupon.createdAt.desc()
            }
        }.toTypedArray()
    }
}

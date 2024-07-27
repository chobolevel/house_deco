package com.movieland.api.service.user

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.service.user.converter.UserProductCouponConverter
import com.movieland.api.service.user.query.UserProductCouponQueryCreator
import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.coupon.ProductCouponFinder
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.product.UserProductCouponFinder
import com.movieland.domain.entity.user.product.UserProductCouponOrderType
import com.movieland.domain.entity.user.product.UserProductCouponQueryFilter
import com.movieland.domain.entity.user.product.UserProductCouponRepository
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.PolicyException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserProductCouponService(
    private val repository: UserProductCouponRepository,
    private val finder: UserProductCouponFinder,
    private val userFinder: UserFinder,
    private val productCouponFinder: ProductCouponFinder,
    private val converter: UserProductCouponConverter,
) {

    @Transactional
    fun publishProductCoupon(userId: Long, productCouponId: Long): Long {
        val user = userFinder.findById(userId)
        val productCoupon = productCouponFinder.findById(productCouponId)
        val isAlreadyPublished = finder.existsByUserIdAndProductCouponId(user.id!!, productCoupon.id!!)
        if (isAlreadyPublished) {
            throw PolicyException(
                status = HttpStatus.BAD_REQUEST,
                errorCode = ErrorCode.PRODUCT_COUPON_IS_ALREADY_PUBLISHED,
                message = "이미 발급된 쿠폰입니다."
            )
        }
        val userProductCoupon = converter.convert(user, productCoupon)
        return repository.save(userProductCoupon).id!!
    }

    @Transactional(readOnly = true)
    fun searchUserProductCouponList(
        queryFilter: UserProductCouponQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserProductCouponOrderType>?
    ): PaginationResponseDto {
        val userProductCouponList = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = userProductCouponList.map { converter.convert(it) },
            totalCount = totalCount
        )
    }
}

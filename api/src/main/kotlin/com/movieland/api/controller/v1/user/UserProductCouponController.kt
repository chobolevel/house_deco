package com.movieland.api.controller.v1.user

import com.movieland.api.annorations.HasAuthorityUser
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.security.UserDetailsImpl
import com.movieland.api.service.user.UserProductCouponService
import com.movieland.api.service.user.query.UserProductCouponQueryCreator
import com.movieland.domain.entity.product.coupon.ProductCoupon
import com.movieland.domain.entity.user.product.UserProductCouponOrderType
import com.movieland.domain.getUserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Tag(name = "UserProductCoupon (회원 상품 쿠폰)", description = "회원 상품 쿠폰 관리 API")
@RestController
@RequestMapping("/api/v1")
class UserProductCouponController(
    private val service: UserProductCouponService,
    private val queryCreator: UserProductCouponQueryCreator
) {

    @HasAuthorityUser
    @Operation(summary = "회원 상품 쿠폰 발급 API")
    @GetMapping("/users/products/coupons/{couponId}/publish")
    fun publishProductCoupon(
        principal: Principal,
        @PathVariable(name = "couponId") productCouponId: Long
    ): ResponseEntity<ResultResponse> {
        val result = service.publishProductCoupon(principal.getUserId(), productCouponId)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "회원 쿠폰 목록 조회 API")
    @GetMapping("/users/products/coupons")
    fun searchMyProductCouponList(
        principal: Principal,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<UserProductCouponOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(principal.getUserId())
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchUserProductCouponList(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

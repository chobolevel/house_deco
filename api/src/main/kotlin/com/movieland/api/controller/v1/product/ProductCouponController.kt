package com.movieland.api.controller.v1.product

import com.movieland.api.annorations.HasAuthorityAdmin
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.coupon.CreateProductCouponRequestDto
import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto
import com.movieland.api.service.product.ProductCouponService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "ProductCoupon (상품 쿠폰)", description = "상품 쿠폰 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductCouponController(
    private val service: ProductCouponService
) {

    @HasAuthorityAdmin
    @Operation(summary = "상품 쿠폰 등록 API")
    @PostMapping("/products/{productId}/coupons")
    fun createProductCoupon(
        @PathVariable productId: Long,
        @Valid @RequestBody
        request: CreateProductCouponRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createProductCoupon(productId, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 쿠폰 정보 수정 API")
    @PutMapping("/products/{productId}/coupons/{couponId}")
    fun updateProductCoupon(
        @PathVariable productId: Long,
        @PathVariable couponId: Long,
        @Valid @RequestBody
        request: UpdateProductCouponRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProductCoupon(productId, couponId, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 쿠폰 삭제 API")
    @DeleteMapping("/products/{productId}/coupons/{couponId}")
    fun deleteProductCoupon(
        @PathVariable productId: Long,
        @PathVariable couponId: Long
    ): ResponseEntity<ResultResponse> {
        val result = service.deleteProductCoupon(productId, couponId)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

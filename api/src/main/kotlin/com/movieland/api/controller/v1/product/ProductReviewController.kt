package com.movieland.api.controller.v1.product

import com.movieland.api.annorations.HasAuthorityUser
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.review.CreateProductReviewRequestDto
import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto
import com.movieland.api.service.product.ProductReviewService
import com.movieland.api.service.product.query.ProductReviewQueryCreator
import com.movieland.domain.entity.product.review.ProductReviewOrderType
import com.movieland.domain.getUserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Tag(name = "ProductReview(상품 후기)", description = "상품 후기 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductReviewController(
    private val service: ProductReviewService,
    private val queryCreator: ProductReviewQueryCreator
) {

    @HasAuthorityUser
    @Operation(summary = "상품 후기 등록 API")
    @PostMapping("/products/{productId}/reviews")
    fun createProductReview(
        principal: Principal,
        @PathVariable productId: Long,
        @Valid @RequestBody
        request: CreateProductReviewRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createProductReview(principal.getUserId(), productId, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 후기 목록 조회 API")
    @GetMapping("/products/{productId}/reviews")
    fun searchProductReviewList(
        @PathVariable productId: Long,
        @RequestParam(required = false) userId: Long?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<ProductReviewOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(userId, productId)
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchProductReviewList(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "상품 후기 단건 조회 API")
    @GetMapping("/products/{productId}/reviews/{productReviewId}")
    fun fetchProductReview(
        principal: Principal,
        @PathVariable productId: Long,
        @PathVariable productReviewId: Long
    ): ResponseEntity<ResultResponse> {
        val result = service.fetchProductReview(principal.getUserId(), productId, productReviewId)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "상품 후기 수정 API")
    @PutMapping("/products/{productId}/reviews/{productReviewId}")
    fun updateProductReview(
        principal: Principal,
        @PathVariable productId: Long,
        @PathVariable productReviewId: Long,
        @Valid @RequestBody
        request: UpdateProductReviewRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProductReview(principal.getUserId(), productId, productReviewId, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityUser
    @Operation(summary = "상품 후기 삭제 API")
    @DeleteMapping("/products/{productId}/reviews/{productReviewId}")
    fun deleteProductReview(
        principal: Principal,
        @PathVariable productId: Long,
        @PathVariable productReviewId: Long
    ): ResponseEntity<ResultResponse> {
        val result = service.deleteProductReview(principal.getUserId(), productId, productReviewId)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

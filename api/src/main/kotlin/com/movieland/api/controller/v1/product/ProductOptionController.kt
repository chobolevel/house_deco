package com.movieland.api.controller.v1.product

import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.option.CreateProductOptionRequestDto
import com.movieland.api.dto.product.option.UpdateProductOptionRequestDto
import com.movieland.api.service.product.ProductOptionService
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

@Tag(name = "ProductOption (상품 옵션)", description = "상품 옵션 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductOptionController(
    private val service: ProductOptionService
) {

    @Operation(summary = "상품 옵션 등록 API")
    @PostMapping("/products/{productId}/options")
    fun createProductOption(
        @PathVariable productId: Long,
        @Valid @RequestBody
        request: CreateProductOptionRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createProductOption(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 옵션 수정 API")
    @PutMapping("/products/{productId}/options/{optionId}")
    fun updateProductOption(
        @PathVariable productId: Long,
        @PathVariable optionId: Long,
        @Valid @RequestBody
        request: UpdateProductOptionRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProductOption(optionId, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 옵션 삭제 API")
    @DeleteMapping("/products/{productId}/options/{optionId}")
    fun deleteProductOption(
        @PathVariable productId: Long,
        @PathVariable optionId: Long
    ): ResponseEntity<ResultResponse> {
        val result = service.deleteProductOption(productId, optionId)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

package com.movieland.api.controller.v1.product

import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.image.CreateProductImageRequestDto
import com.movieland.api.dto.product.image.UpdateProductImageRequestDto
import com.movieland.api.service.product.ProductImageService
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

@Tag(name = "ProductImage (상품 이미지)", description = "상품 이미지 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductImageController(
    private val service: ProductImageService
) {

    @Operation(summary = "상품 이미지 추가 API")
    @PostMapping("/products/images")
    fun createProductImage(@Valid @RequestBody request: CreateProductImageRequestDto): ResponseEntity<ResultResponse> {
        val result = service.createProductImage(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 이미지 수정 API")
    @PutMapping("/products/images/{id}")
    fun updateProductImage(
        @PathVariable id: String,
        @Valid @RequestBody request: UpdateProductImageRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProductImage(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 이미지 삭제 API")
    @DeleteMapping("/products/images/{id}")
    fun deleteProductImage(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.deleteProductImage(id)
        return ResponseEntity.ok(ResultResponse(result))
    }


}

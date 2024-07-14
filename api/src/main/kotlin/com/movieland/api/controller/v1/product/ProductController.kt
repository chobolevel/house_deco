package com.movieland.api.controller.v1.product

import com.movieland.api.annorations.HasAuthorityAdmin
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.CreateProductRequestDto
import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.api.service.product.ProductService
import com.movieland.api.service.product.query.ProductQueryCreator
import com.movieland.domain.entity.product.ProductOrderType
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

@Tag(name = "Product (상품)", description = "상품 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductController(
    private val service: ProductService,
    private val queryCreator: ProductQueryCreator
) {

    @HasAuthorityAdmin
    @Operation(summary = "상품 정보 등록 API")
    @PostMapping("/products")
    fun createProduct(
        @Valid @RequestBody
        request: CreateProductRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createProduct(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 정보 목록 조회 API")
    @GetMapping("/products")
    fun searchProductList(
        @RequestParam(required = false) categoryId: String?,
        @RequestParam(required = false) brandId: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<ProductOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(categoryId, brandId, name)
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchProductList(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 정보 단건 조회 API")
    @GetMapping("/products/{id}")
    fun fetchProduct(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.fetchProduct(id)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 정보 수정 API")
    @PutMapping("/products/{id}")
    fun updateProduct(
        @PathVariable id: String,
        @Valid @RequestBody
        request: UpdateProductRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProduct(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 정보 삭제 API")
    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.deleteProduct(id)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

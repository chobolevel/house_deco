package com.movieland.api.controller.v1.product

import com.movieland.api.annorations.HasAuthorityAdmin
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.dto.product.category.CreateProductCategoryRequestDto
import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.api.service.product.category.ProductCategoryQueryCreator
import com.movieland.api.service.product.category.ProductCategoryService
import com.movieland.domain.entity.product.category.ProductCategoryOrderType
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

@Tag(name = "ProductCategory (상품 카테고리)", description = "상품 카테고리 관리 API")
@RestController
@RequestMapping("/api/v1")
class ProductCategoryController(
    private val service: ProductCategoryService,
    private val queryCreator: ProductCategoryQueryCreator
) {

    @HasAuthorityAdmin
    @Operation(summary = "상품 카테고리 등록 API")
    @PostMapping("/products/categories")
    fun createProductCategory(@Valid @RequestBody request: CreateProductCategoryRequestDto): ResponseEntity<ResultResponse> {
        val result = service.createProductCategory(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 카테고리 목록 조회 API")
    @GetMapping("/products/categories")
    fun searchProductCategoryList(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<ProductCategoryOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(name)
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchProductCategoryList(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "상품 카테고리 단건 조회 API")
    @GetMapping("/products/categories/{id}")
    fun fetchProductCategory(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.fetchProductCategory(id)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 카테고리 정보 수정 API")
    @PutMapping("/products/categories/{id}")
    fun updateProductCategory(
        @PathVariable id: String,
        @Valid @RequestBody request: UpdateProductCategoryRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateProductCategory(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "상품 카테고리 삭제 API")
    @DeleteMapping("/products/categories/{id}")
    fun deleteProductCategory(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.deleteProductCategory(id)
        return ResponseEntity.ok(ResultResponse(result))
    }

}

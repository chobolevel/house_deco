package com.movieland.api.controller.v1.brand

import com.movieland.api.annorations.HasAuthorityAdmin
import com.movieland.api.dto.brand.CreateBrandRequestDto
import com.movieland.api.dto.brand.UpdateBrandRequestDto
import com.movieland.api.dto.common.ResultResponse
import com.movieland.api.service.brand.BrandQueryCreator
import com.movieland.api.service.brand.BrandService
import com.movieland.domain.entity.brand.BrandOrderType
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

@Tag(name = "Brand (브랜드)", description = "브랜드 정보 관리 API")
@RestController
@RequestMapping("/api/v1")
class BrandController(
    private val service: BrandService,
    private val queryCreator: BrandQueryCreator
) {

    @HasAuthorityAdmin
    @Operation(summary = "브랜드 등록 API")
    @PostMapping("/brands")
    fun createBrand(
        @Valid @RequestBody
        request: CreateBrandRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.createBrand(request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "브랜드 정보 목록 조회 API")
    @GetMapping("/brands")
    fun searchBrandList(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) skipCount: Long?,
        @RequestParam(required = false) limitCount: Long?,
        @RequestParam(required = false) orderTypes: List<BrandOrderType>?
    ): ResponseEntity<ResultResponse> {
        val queryFilter = queryCreator.createQueryFilter(name)
        val pagination = queryCreator.createPaginationFilter(skipCount, limitCount)
        val result = service.searchBrandList(queryFilter, pagination, orderTypes)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @Operation(summary = "브랜드 정보 단건 조회 API")
    @GetMapping("/brands/{id}")
    fun fetchBrand(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.fetchBrand(id)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "브랜드 정보 수정 API")
    @PutMapping("/brands/{id}")
    fun updateBrand(
        @PathVariable id: String,
        @Valid @RequestBody
        request: UpdateBrandRequestDto
    ): ResponseEntity<ResultResponse> {
        val result = service.updateBrand(id, request)
        return ResponseEntity.ok(ResultResponse(result))
    }

    @HasAuthorityAdmin
    @Operation(summary = "브랜드 정보 삭제 API")
    @DeleteMapping("/brands/{id}")
    fun deleteBrand(@PathVariable id: String): ResponseEntity<ResultResponse> {
        val result = service.deleteBrand(id)
        return ResponseEntity.ok(ResultResponse(result))
    }
}

package com.movieland.api.service.brand

import com.movieland.api.dto.brand.BrandResponseDto
import com.movieland.api.dto.brand.CreateBrandRequestDto
import com.movieland.api.dto.brand.UpdateBrandRequestDto
import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.service.brand.converter.BrandConverter
import com.movieland.api.service.brand.updater.BrandUpdatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.brand.BrandFinder
import com.movieland.domain.entity.brand.BrandOrderType
import com.movieland.domain.entity.brand.BrandQueryFilter
import com.movieland.domain.entity.brand.BrandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandService(
    private val repository: BrandRepository,
    private val finder: BrandFinder,
    private val converter: BrandConverter,
    private val updaters: List<BrandUpdatable>
) {

    @Transactional
    fun createBrand(request: CreateBrandRequestDto): String {
        val brand = converter.convert(request)
        return repository.save(brand).id
    }

    @Transactional(readOnly = true)
    fun searchBrandList(
        queryFilter: BrandQueryFilter,
        pagination: Pagination,
        orderTypes: List<BrandOrderType>?
    ): PaginationResponseDto {
        val brandList = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = brandList.map { converter.convert(it) },
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    fun fetchBrand(id: String): BrandResponseDto {
        val brand = finder.findById(id)
        return converter.convert(brand)
    }

    @Transactional
    fun updateBrand(id: String, request: UpdateBrandRequestDto): String {
        val brand = finder.findById(id)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, brand) }
        return brand.id
    }

    @Transactional
    fun deleteBrand(id: String): Boolean {
        val brand = finder.findById(id)
        repository.delete(brand)
        return true
    }

}

package com.movieland.api.service.product

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.product.CreateProductRequestDto
import com.movieland.api.dto.product.ProductResponseDto
import com.movieland.api.service.product.converter.ProductConverter
import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.ProductOrderType
import com.movieland.domain.entity.product.ProductQueryFilter
import com.movieland.domain.entity.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val repository: ProductRepository,
    private val finder: ProductFinder,
    private val converter: ProductConverter
) {

    @Transactional
    fun createProduct(request: CreateProductRequestDto): String {
        val product = converter.convert(request)
        return repository.save(product).id
    }

    @Transactional(readOnly = true)
    fun searchProductList(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>?
    ): PaginationResponseDto {
        val productList = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = productList.map { converter.convert(it) },
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    fun fetchProduct(id: String): ProductResponseDto {
        val product = finder.findById(id)
        return converter.convert(product)
    }

}

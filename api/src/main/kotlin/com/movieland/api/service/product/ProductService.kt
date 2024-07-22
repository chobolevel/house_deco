package com.movieland.api.service.product

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.product.CreateProductRequestDto
import com.movieland.api.dto.product.ProductResponseDto
import com.movieland.api.dto.product.UpdateProductRequestDto
import com.movieland.api.service.product.converter.ProductConverter
import com.movieland.api.service.product.updater.ProductUpdatable
import com.movieland.api.service.product.validator.ProductUpdateValidatable
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
    private val converter: ProductConverter,
    private val updateValidators: List<ProductUpdateValidatable>,
    private val updaters: List<ProductUpdatable>
) {

    @Transactional
    fun createProduct(request: CreateProductRequestDto): Long {
        val product = converter.convert(request)
        return repository.save(product).id!!
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
    fun fetchProduct(id: Long): ProductResponseDto {
        val product = finder.findById(id)
        return converter.convert(product)
    }

    @Transactional
    fun updateProduct(id: Long, request: UpdateProductRequestDto): Long {
        updateValidators.forEach { it.validate(request) }
        val product = finder.findById(id)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, product) }
        return product.id!!
    }

    @Transactional
    fun deleteProduct(id: Long): Boolean {
        val product = finder.findById(id)
        repository.delete(product)
        return true
    }
}

package com.movieland.api.service.product.category

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.product.category.CreateProductCategoryRequestDto
import com.movieland.api.dto.product.category.ProductCategoryResponseDto
import com.movieland.api.dto.product.category.UpdateProductCategoryRequestDto
import com.movieland.api.service.product.category.converter.ProductCategoryConverter
import com.movieland.api.service.product.category.updater.ProductCategoryUpdatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.category.ProductCategoryFinder
import com.movieland.domain.entity.product.category.ProductCategoryOrderType
import com.movieland.domain.entity.product.category.ProductCategoryQueryFilter
import com.movieland.domain.entity.product.category.ProductCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductCategoryService(
    private val repository: ProductCategoryRepository,
    private val finder: ProductCategoryFinder,
    private val converter: ProductCategoryConverter,
    private val updaters: List<ProductCategoryUpdatable>
) {

    fun createProductCategory(request: CreateProductCategoryRequestDto): String {
        val productCategory = converter.convert(request)
        request.parentId?.let {
            val parent = finder.findById(it)
            productCategory.setBy(parent)
        }
        return repository.save(productCategory).id
    }

    fun searchProductCategoryList(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>?
    ): PaginationResponseDto {
        val productCategoryList = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = productCategoryList.map { converter.convert(it) },
            totalCount = totalCount
        )
    }

    fun fetchProductCategory(id: String): ProductCategoryResponseDto {
        val productCategory = finder.findById(id)
        return converter.convert(productCategory)
    }

    fun updateProductCategory(id: String, request: UpdateProductCategoryRequestDto): String {
        val productCategory = finder.findById(id)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, productCategory) }
        return productCategory.id
    }

    fun deleteProductCategory(id: String): Boolean {
        val productCategory = finder.findById(id)
        repository.delete(productCategory)
        return true
    }
}

package com.movieland.domain.entity.product.category

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.category.QProductCategory.productCategory
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductCategoryFinder(
    private val repository: ProductCategoryRepository,
    private val customRepository: ProductCategoryCustomRepository
) {

    fun findById(id: String): ProductCategory {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_CATEGORY_IS_NOT_FOUND,
            message = "상품 카테고리를 찾을 수 없습니다."
        )
    }

    fun search(
        queryFilter: ProductCategoryQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductCategoryOrderType>?
    ): List<ProductCategory> {
        val orderSpecifiers = orderSpecifier(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: ProductCategoryQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    private fun orderSpecifier(orderTypes: List<ProductCategoryOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                ProductCategoryOrderType.CREATED_AT_ASC -> productCategory.createdAt.asc()
                ProductCategoryOrderType.CREATED_AT_DESC -> productCategory.createdAt.desc()
            }
        }.toTypedArray()
    }

}

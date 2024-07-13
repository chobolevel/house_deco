package com.movieland.domain.entity.product

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.QProduct.product
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class ProductFinder(
    private val repository: ProductRepository,
    private val customRepository: ProductCustomRepository
) {

    @Throws(DataNotFoundException::class)
    fun findById(id: String): Product {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.PRODUCT_IS_NOT_FOUND,
            message = "상품을 찾을 수 없습니다."
        )
    }

    fun search(
        queryFilter: ProductQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductOrderType>?
    ): List<Product> {
        val orderSpecifiers = orderSpecifier(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: ProductQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    private fun orderSpecifier(orderTypes: List<ProductOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                ProductOrderType.REVIEW_COUNT_ASC -> product.reviewCount.asc()
                ProductOrderType.REVIEW_COUNT_DESC -> product.reviewCount.desc()
                ProductOrderType.REVIEW_AVERAGE_ASC -> product.reviewAverage.asc()
                ProductOrderType.REVIEW_AVERAGE_DESC -> product.reviewAverage.desc()
                ProductOrderType.SALES_COUNT_ASC -> product.salesCount.asc()
                ProductOrderType.SALES_COUNT_DESC -> product.salesCount.desc()
                ProductOrderType.PRIORITY_ASC -> product.priority.asc()
                ProductOrderType.PRIORITY_DESC -> product.priority.desc()
                ProductOrderType.CREATED_AT_ASC -> product.createdAt.asc()
                ProductOrderType.CREATED_AT_DESC -> product.createdAt.desc()
            }
        }.toTypedArray()
    }
}

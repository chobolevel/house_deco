package com.movieland.domain.entity.brand

import com.movieland.domain.Pagination
import com.movieland.domain.entity.brand.QBrand.brand
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BrandFinder(
    private val repository: BrandRepository,
    private val customRepository: BrandCustomRepository
) {

    fun findById(id: String): Brand {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.BRAND_IS_NOT_FOUND,
            message = "브랜드 정보를 찾을 수 없습니다."
        )
    }

    fun search(queryFilter: BrandQueryFilter, pagination: Pagination, orderTypes: List<BrandOrderType>?): List<Brand> {
        val orderSpecifiers = orderSpecifier(orderTypes ?: emptyList())
        return customRepository.searchByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun searchCount(queryFilter: BrandQueryFilter): Long {
        return customRepository.countByPredicates(queryFilter.toPredicates())
    }

    private fun orderSpecifier(orderTypes: List<BrandOrderType>): Array<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                BrandOrderType.CREATED_AT_ASC -> brand.createdAt.asc()
                BrandOrderType.CREATED_AT_DESC -> brand.createdAt.desc()
            }
        }.toTypedArray()
    }
}

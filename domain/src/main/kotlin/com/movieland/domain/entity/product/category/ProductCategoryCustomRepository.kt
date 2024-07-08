package com.movieland.domain.entity.product.category

import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.category.QProductCategory.productCategory
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductCategoryCustomRepository : QuerydslRepositorySupport(ProductCategory::class.java) {

    fun searchByPredicates(
        predicates: Array<BooleanExpression>,
        pagination: Pagination,
        orderSpecifiers: Array<OrderSpecifier<*>>
    ): List<ProductCategory> {
        return from(productCategory)
            .where(productCategory.parent.id.isNull, *predicates)
            .orderBy(*orderSpecifiers)
            .offset(pagination.skip)
            .limit(pagination.limit)
            .fetch()
    }

    fun countByPredicates(predicates: Array<BooleanExpression>): Long {
        return from(productCategory)
            .where(productCategory.parent.id.isNull, *predicates)
            .fetchCount()
    }
}

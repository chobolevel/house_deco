package com.movieland.domain.entity.product.category

import com.movieland.domain.entity.product.category.QProductCategory.productCategory
import com.querydsl.core.types.dsl.BooleanExpression

class ProductCategoryQueryFilter(
    val name: String?
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let { productCategory.name.eq(it) }
        ).toTypedArray()
    }
}

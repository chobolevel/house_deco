package com.movieland.domain.entity.product

import com.movieland.domain.entity.product.QProduct.product
import com.querydsl.core.types.dsl.BooleanExpression

class ProductQueryFilter(
    private val productCategoryId: String?,
    private val name: String?,
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            productCategoryId?.let { product.productCategory.id.eq(it) },
            name?.let { product.name.eq(it) }
        ).toTypedArray()
    }

}

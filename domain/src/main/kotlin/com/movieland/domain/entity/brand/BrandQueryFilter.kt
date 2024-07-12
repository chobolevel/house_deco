package com.movieland.domain.entity.brand

import com.movieland.domain.entity.brand.QBrand.brand
import com.querydsl.core.types.dsl.BooleanExpression

class BrandQueryFilter(
    val name: String?,
) {

    fun toPredicates(): Array<BooleanExpression> {
        return listOfNotNull(
            name?.let { brand.name.eq(it) }
        ).toTypedArray()
    }
}

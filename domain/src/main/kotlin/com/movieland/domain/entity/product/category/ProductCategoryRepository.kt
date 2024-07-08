package com.movieland.domain.entity.product.category

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryRepository : JpaRepository<ProductCategory, String> {
}

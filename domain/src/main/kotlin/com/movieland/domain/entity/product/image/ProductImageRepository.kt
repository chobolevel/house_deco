package com.movieland.domain.entity.product.image

import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage, String> {
}

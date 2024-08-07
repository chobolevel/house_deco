package com.movieland.domain.entity.product.review

import org.springframework.data.jpa.repository.JpaRepository

interface ProductReviewRepository : JpaRepository<ProductReview, Long> {

    fun findByIdAndUserIdAndDeletedFalse(id: Long, userId: Long): ProductReview?

    fun existsByUserIdAndProductIdAndDeletedFalse(userId: Long, productId: Long): Boolean
}

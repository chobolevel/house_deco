package com.movieland.api.service.product.converter

import com.movieland.api.dto.product.review.CreateProductReviewRequestDto
import com.movieland.api.dto.product.review.ProductReviewResponseDto
import com.movieland.api.service.user.converter.UserConverter
import com.movieland.domain.entity.product.Product
import com.movieland.domain.entity.product.review.ProductReview
import com.movieland.domain.entity.user.User
import org.springframework.stereotype.Component

@Component
class ProductReviewConverter(
    private val userConverter: UserConverter,
    private val productConverter: ProductConverter,
) {

    fun convert(request: CreateProductReviewRequestDto, user: User, product: Product): ProductReview {
        return ProductReview(
            rating = request.rating,
            comment = request.comment
        ).also {
            it.setBy(user)
            it.setBy(product)
        }
    }

    fun convert(entity: ProductReview): ProductReviewResponseDto {
        return ProductReviewResponseDto(
            id = entity.id!!,
            reviewer = userConverter.convert(entity.user!!),
            productInfo = productConverter.convert(entity.product!!),
            rating = entity.rating,
            comment = entity.comment,
            createdAt = entity.createdAt!!.toInstant().toEpochMilli(),
            updatedAt = entity.updatedAt!!.toInstant().toEpochMilli()
        )
    }
}

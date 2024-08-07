package com.movieland.api.service.product

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.product.review.CreateProductReviewRequestDto
import com.movieland.api.dto.product.review.ProductReviewResponseDto
import com.movieland.api.dto.product.review.UpdateProductReviewRequestDto
import com.movieland.api.service.product.converter.ProductReviewConverter
import com.movieland.api.service.product.updater.review.ProductReviewUpdatable
import com.movieland.api.service.product.validator.review.UpdateProductReviewValidatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.review.ProductReviewFinder
import com.movieland.domain.entity.product.review.ProductReviewOrderType
import com.movieland.domain.entity.product.review.ProductReviewQueryFilter
import com.movieland.domain.entity.product.review.ProductReviewRepository
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.PolicyException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductReviewService(
    private val repository: ProductReviewRepository,
    private val finder: ProductReviewFinder,
    private val userFinder: UserFinder,
    private val productFinder: ProductFinder,
    private val converter: ProductReviewConverter,
    private val updateValidators: List<UpdateProductReviewValidatable>,
    private val updaters: List<ProductReviewUpdatable>
) {

    @Transactional
    fun createProductReview(userId: Long, productId: Long, request: CreateProductReviewRequestDto): Long {
        val user = userFinder.findById(userId)
        val product = productFinder.findById(productId)
        val exists = finder.existsByUserIdAndProductId(user.id!!, product.id!!)
        if (exists) {
            throw PolicyException(
                status = HttpStatus.BAD_REQUEST,
                errorCode = ErrorCode.PRODUCT_REVIEW_IS_ALREADY_WRITTEN,
                message = "이미 상품 후기를 작성하였습니다."
            )
        }
        val productReview = converter.convert(request, user, product)
        // product review_count review_average sync
        return repository.save(productReview).id!!
    }

    @Transactional(readOnly = true)
    fun searchProductReviewList(
        queryFilter: ProductReviewQueryFilter,
        pagination: Pagination,
        orderTypes: List<ProductReviewOrderType>?
    ): PaginationResponseDto {
        val goodsReviewList = finder.search(queryFilter, pagination, orderTypes)
        val totalCount = finder.searchCount(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = goodsReviewList.map { converter.convert(it) },
            totalCount = totalCount
        )
    }

    @Transactional(readOnly = true)
    fun fetchProductReview(userId: Long, productId: Long, productReviewId: Long): ProductReviewResponseDto {
        val productReview = finder.findByIdAndUserId(productReviewId, userId)
        return converter.convert(productReview)
    }

    @Transactional
    fun updateProductReview(
        userId: Long,
        productId: Long,
        productReviewId: Long,
        request: UpdateProductReviewRequestDto
    ): Long {
        updateValidators.forEach { it.validate(request) }
        val productReview = finder.findByIdAndUserId(productReviewId, userId)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, productReview) }
        // product review_count review_average sync
        return productReview.id!!
    }

    @Transactional
    fun deleteProductReview(userId: Long, productId: Long, productReviewId: Long): Boolean {
        val productReview = finder.findByIdAndUserId(productReviewId, userId)
        repository.delete(productReview)
        // product review_count review_average sync
        return true
    }
}

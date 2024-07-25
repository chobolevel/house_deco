package com.movieland.api.service.product

import com.movieland.api.dto.product.coupon.CreateProductCouponRequestDto
import com.movieland.api.dto.product.coupon.UpdateProductCouponRequestDto
import com.movieland.api.service.product.converter.ProductCouponConverter
import com.movieland.api.service.product.updater.ProductCouponUpdatable
import com.movieland.api.service.product.validator.coupon.CreateProductCouponValidatable
import com.movieland.api.service.product.validator.coupon.UpdateProductCouponValidatable
import com.movieland.domain.entity.product.ProductFinder
import com.movieland.domain.entity.product.coupon.ProductCouponFinder
import com.movieland.domain.entity.product.coupon.ProductCouponRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCouponService(
    private val repository: ProductCouponRepository,
    private val finder: ProductCouponFinder,
    private val productFinder: ProductFinder,
    private val createValidators: List<CreateProductCouponValidatable>,
    private val convert: ProductCouponConverter,
    private val updateValidators: List<UpdateProductCouponValidatable>,
    private val updaters: List<ProductCouponUpdatable>
) {

    @Transactional
    fun createProductCoupon(productId: Long, request: CreateProductCouponRequestDto): Long {
        createValidators.forEach { it.validate(request) }
        val product = productFinder.findById(productId)
        val productCoupon = convert.convert(request, product)
        return repository.save(productCoupon).id!!
    }

    @Transactional
    fun updateProductCoupon(productId: Long, id: Long, request: UpdateProductCouponRequestDto): Long {
        updateValidators.forEach { it.validate(request) }
        val productCoupon = finder.findByIdAndProductId(id, productId)
        updaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, productCoupon) }
        return productCoupon.id!!
    }

    @Transactional
    fun deleteProductCoupon(productId: Long, id: Long): Boolean {
        val productCoupon = finder.findByIdAndProductId(id, productId)
        repository.delete(productCoupon)
        return true
    }
}

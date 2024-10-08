package com.movieland.domain.entity.product

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.brand.Brand
import com.movieland.domain.entity.product.category.ProductCategory
import com.movieland.domain.entity.product.coupon.ProductCoupon
import com.movieland.domain.entity.product.image.ProductImage
import com.movieland.domain.entity.product.image.ProductImageType
import com.movieland.domain.entity.product.option.ProductOption
import com.movieland.domain.entity.product.option.ProductOptionType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "products")
@Audited
class Product(
    @Column(nullable = false)
    var name: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ProductStatusType
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    var productCategory: ProductCategory? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    var brand: Brand? = null

    @Column(nullable = false)
    var reviewCount: Int = 0

    @Column(nullable = false)
    var reviewAverage: Double = 0.0

    @Column(nullable = false)
    var salesCount: Int = 0

    @Column(nullable = false)
    var priority: Int = 0

    @Column(nullable = false)
    var deleted: Boolean = false

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images = mutableListOf<ProductImage>()

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    val options = mutableListOf<ProductOption>()

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    val coupons = mutableListOf<ProductCoupon>()

    fun setBy(productCategory: ProductCategory) {
        if (this.productCategory != productCategory) {
            this.productCategory = productCategory
        }
    }

    fun setBy(brand: Brand) {
        if (this.brand != brand) {
            this.brand = brand
        }
        // 주인 객체에서 슬레이브 객체까지 관리하도록 하는 것이 좋음
        if (brand.products.contains(this).not()) {
            brand.products.add(this)
        }
    }

    fun addImage(productImage: ProductImage) {
        if (this.images.contains(productImage).not()) {
            this.images.add(productImage)
        }
    }

    fun addCoupon(productCoupon: ProductCoupon) {
        if (this.coupons.contains(productCoupon).not()) {
            this.coupons.add(productCoupon)
        }
    }

    fun getMainImages(): List<ProductImage> {
        return this.images.filter { it.type != ProductImageType.DETAIL }
    }

    fun getDetailImages(): List<ProductImage> {
        return this.images.filter { it.type == ProductImageType.DETAIL }
    }

    fun getRequiredOptions(): List<ProductOption> {
        return this.options.filter { it.type == ProductOptionType.REQUIRED }
    }

    fun getOptionalOptions(): List<ProductOption> {
        return this.options.filter { it.type == ProductOptionType.OPTIONAL }
    }

    fun deleteMainImages() {
        this.images.filter { it.type != ProductImageType.DETAIL }.forEach { it.deleted = true }
    }

    fun deleteDetailImages() {
        this.images.filter { it.type == ProductImageType.DETAIL }.forEach { it.deleted = true }
    }
}

enum class ProductStatusType {
    SALE,
    PREPARING,
    CLOSED
}

enum class ProductOrderType {
    REVIEW_COUNT_ASC,
    REVIEW_COUNT_DESC,
    REVIEW_AVERAGE_ASC,
    REVIEW_AVERAGE_DESC,
    SALES_COUNT_ASC,
    SALES_COUNT_DESC,
    PRIORITY_ASC,
    PRIORITY_DESC,
    CREATED_AT_ASC,
    CREATED_AT_DESC,
}

enum class ProductUpdateMask {
    CATEGORY,
    BRAND,
    NAME,
    STATUS,
    PRIORITY,
    IMAGES,
    DETAIL_IMAGES
}

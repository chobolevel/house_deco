package com.movieland.domain.entity.product.coupon

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.Product
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "product_coupons")
@Audited
@SQLDelete(sql = "UPDATE product_coupons SET deleted = true WHERE id = ?")
class ProductCoupon(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var minPurchaseAmount: Int,
    @Column(nullable = false)
    var discountPercent: Int,
    @Column(nullable = false)
    var maxDiscountAmount: Int,
    @Column(nullable = false)
    var expiredAt: OffsetDateTime
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    fun setBy(product: Product) {
        if (this.product != product) {
            this.product = product
            product.addCoupon(this)
        }
    }
}

enum class ProductCouponUpdateMask {
    NAME,
    MIN_PURCHASE_AMOUNT,
    DISCOUNT_PERCENT,
    MAX_DISCOUNT_AMOUNT,
    EXPIRED_AT
}

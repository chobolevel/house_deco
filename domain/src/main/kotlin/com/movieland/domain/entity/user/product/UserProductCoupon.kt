package com.movieland.domain.entity.user.product

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.coupon.ProductCoupon
import com.movieland.domain.entity.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "user_product_coupons")
@Audited
class UserProductCoupon : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_coupon_id")
    var productCoupon: ProductCoupon? = null

    @Column(nullable = false)
    var used: Boolean = false

    fun setBy(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }

    fun setBy(productCoupon: ProductCoupon) {
        if (this.productCoupon != productCoupon) {
            this.productCoupon = productCoupon
        }
    }
}

enum class UserProductCouponOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

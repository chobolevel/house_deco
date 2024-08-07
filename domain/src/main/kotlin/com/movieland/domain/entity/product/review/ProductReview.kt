package com.movieland.domain.entity.product.review

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.Product
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
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "product_reviews")
@Audited
@SQLDelete(sql = "UPDATE product_reviews deleted = true WHERE id = ?")
class ProductReview(
    @Column(nullable = false)
    var rating: Double,
    @Column(nullable = false)
    var comment: String,
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    fun setBy(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }

    fun setBy(product: Product) {
        if (this.product != product) {
            this.product = product
        }
    }
}

enum class ProductReviewOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

enum class ProductReviewUpdateMask {
    RATING,
    COMMENT
}

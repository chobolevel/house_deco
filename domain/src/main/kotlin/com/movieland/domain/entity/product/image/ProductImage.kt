package com.movieland.domain.entity.product.image

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.Product
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
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "product_images")
@Audited
@SQLDelete(sql = "UPDATE product_images SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
class ProductImage(
    @Column(nullable = false)
    var originUrl: String,
    @Column(nullable = false)
    var name: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: ProductImageType,
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
        }
        // 매핑 객체 데이터 관리는 주인 객체가 수행!
        product.addImage(this)
    }
}

enum class ProductImageType {
    MAIN,
    SUB,
    DETAIL
}

enum class ProductImageUpdateMask {
    ORIGIN_URL,
    NAME,
    TYPE
}

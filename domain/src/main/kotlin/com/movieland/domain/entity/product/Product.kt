package com.movieland.domain.entity.product

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.category.ProductCategory
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "products")
@Audited
class Product(
    @Id
    @Column(nullable = false, updatable = false)
    var id: String,
    @Column(nullable = false)
    var name: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ProductStatusType
) : Audit() {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var productCategory: ProductCategory? = null

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

package com.movieland.domain.entity.product.option

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
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "product_options")
@Audited
@SQLDelete(sql = "UPDATE product_options SET deleted = true WHERE id = ?")
class ProductOption(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: ProductOptionType,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var originalPrice: Int,
    @Column(nullable = false)
    var salePrice: Int,
    @Column(nullable = false)
    var stock: Int,
    @Column(nullable = false)
    var order: Int
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    fun setBy(product: Product) {
        if (this.product != product) {
            this.product = product
        }
        if (product.options.contains(this).not()) {
            product.options.add(this)
        }
    }
}

enum class ProductOptionType {
    REQUIRED,
    OPTIONAL
}

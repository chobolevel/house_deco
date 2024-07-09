package com.movieland.domain.entity.product.category

import com.movieland.domain.entity.Audit
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "product_categories")
@Audited
class ProductCategory(
    @Id
    @Column(nullable = false, updatable = false)
    var id: String,
    @Column(nullable = false)
    var name: String
) : Audit() {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: ProductCategory? = null

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    val categories = mutableSetOf<ProductCategory>()

    fun setBy(parent: ProductCategory) {
        if (this.parent != parent) {
            this.parent = parent
        }
    }

    fun resetParent() {
        this.parent = null
    }
}

enum class ProductCategoryOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

enum class ProductCategoryUpdateMask {
    PARENT,
    NAME
}

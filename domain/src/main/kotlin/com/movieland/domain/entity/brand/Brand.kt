package com.movieland.domain.entity.brand

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.product.Product
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "brands")
@Audited
@SQLDelete(sql = "UPDATE brands SET deleted = true WHERE id = ?")
class Brand(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var link: String
) : Audit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    @OneToMany(mappedBy = "brand", cascade = [CascadeType.ALL], orphanRemoval = true)
    var products = mutableListOf<Product>()
}

enum class BrandOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

enum class BrandUpdateMask {
    NAME,
    LINK
}

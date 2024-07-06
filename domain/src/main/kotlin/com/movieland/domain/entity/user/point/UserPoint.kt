package com.movieland.domain.entity.user.point

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.user.User
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
@Table(name = "user_points")
@Audited
class UserPoint(
    @Id
    @Column(nullable = false, updatable = false)
    var id: String? = null,
    @Column(nullable = false)
    var currency: Int,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: UserPointType,
    @Column(nullable = false)
    var description: String
) : Audit() {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    var user: User? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    fun delete() {
        this.user!!.point -= this.currency
        this.deleted = true
    }

    fun setBy(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }
}

enum class UserPointType {
    GREETING,
}

enum class UserPointOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

enum class UserPointUpdateMask {
    CURRENCY,
    TYPE,
    DESCRIPTION
}

package com.movieland.domain.entity.user.image

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "user_images")
@Audited
class UserImage(
    @Id
    @Column(nullable = false, updatable = false)
    var id: String? = null,
    @Column(nullable = false)
    var originUrl: String,
    @Column(nullable = false)
    var name: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: UserImageType = UserImageType.PROFILE,
) : Audit() {

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    var user: User? = null

    @Column(nullable = false)
    var deleted: Boolean = false

    fun delete() {
        this.deleted = true
    }

    fun setBy(user: User) {
        if (this.user != user) {
            this.user = user
        }
    }
}

enum class UserImageType {
    PROFILE
}

enum class UserImageUpdateMask {
    ORIGIN_URL,
    NAME,
}

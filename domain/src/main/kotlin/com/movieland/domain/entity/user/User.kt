package com.movieland.domain.entity.user

import com.movieland.domain.entity.Audit
import com.movieland.domain.entity.user.image.UserImage
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Where
import org.hibernate.envers.Audited
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(value = [AuditingEntityListener::class])
@Entity
@Table(name = "users")
@Audited
@Where(clause = "deleted = false")
class User(
    @Id
    @Column(nullable = false, updatable = false)
    var id: String? = null,
    @Column(nullable = false)
    var email: String? = null,
    @Column(nullable = false)
    var password: String? = null,
    @Column
    var socialId: String? = null,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var loginType: UserLoginType? = null,
    @Column(nullable = false)
    var nickname: String? = null,
    @Column(nullable = false)
    var phone: String? = null,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRoleType = UserRoleType.ROLE_USER,
) : Audit() {

    @Column(nullable = false)
    var deleted: Boolean? = false

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var profileImage: UserImage? = null

    fun resign() {
        this.deleted = true
    }
}

enum class UserLoginType {
    GENERAL,
    KAKAO;

    // equal to java static method
    companion object {
        fun find(value: String): UserLoginType? {
            return values().find { it.name == value }
        }
    }
}

enum class UserRoleType {
    ROLE_USER,
    ROLE_ADMIN
}

enum class UserOrderType {
    CREATED_AT_ASC,
    CREATED_AT_DESC
}

enum class UserUpdateMaskType {
    NICKNAME,
    PHONE
}

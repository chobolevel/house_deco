package com.movieland.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
@Audited
class Audit {

    @Column(nullable = false)
    @CreatedDate
    var createdAt: LocalDateTime? = null
        protected set

    @Column(nullable = false)
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        protected set
}

package com.movieland.domain.entity.user.point

import org.springframework.data.jpa.repository.JpaRepository

interface UserPointRepository : JpaRepository<UserPoint, Long> {

    fun findByIdAndUserId(id: Long, userId: Long): UserPoint?
}

package com.movieland.domain.entity.user.point

import org.springframework.data.jpa.repository.JpaRepository

interface UserPointRepository : JpaRepository<UserPoint, String> {

    fun findByIdAndUserId(id: String, userId: String): UserPoint?

}

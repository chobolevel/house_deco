package com.movieland.domain.entity.user.image

import org.springframework.data.jpa.repository.JpaRepository


interface UserImageRepository : JpaRepository<UserImage, String> {

    fun findByIdAndUserId(id: String, userId: String): UserImage?

}

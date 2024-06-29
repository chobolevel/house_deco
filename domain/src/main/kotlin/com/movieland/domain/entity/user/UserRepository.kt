package com.movieland.domain.entity.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {

    fun existsByEmailAndDeletedIsFalse(email: String): Boolean

    fun existsByNicknameAndDeletedIsFalse(nickname: String): Boolean

    fun existsByPhoneAndDeletedIsFalse(phone: String): Boolean

    fun findByEmailAndLoginType(email: String, loginType: UserLoginType): User?
}

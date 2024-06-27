package com.movieland.domain.entity.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {

  fun existsByUsernameAndDeletedIsFalse(username: String): Boolean

  fun existsBySocialIdAndLoginType(socialId: String, loginType: UserLoginType): Boolean

  fun existsByPhoneAndDeletedIsFalse(phone: String): Boolean
}

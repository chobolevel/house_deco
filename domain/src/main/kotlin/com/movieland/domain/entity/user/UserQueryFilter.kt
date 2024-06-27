package com.movieland.domain.entity.user

import com.movieland.domain.entity.user.QUser.user
import com.querydsl.core.types.dsl.BooleanExpression

data class UserQueryFilter(
  val username: String?,
) {

  fun toPredicates(): List<BooleanExpression> {
    return listOfNotNull(
      username?.let { user.username.eq(it) }
    )
  }
}

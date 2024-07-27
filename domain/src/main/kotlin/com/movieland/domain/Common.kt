package com.movieland.domain

import java.security.Principal

data class Pagination(val skip: Long, val limit: Long)

fun Principal.getUserId(): Long {
    return this.name.toLong()
}

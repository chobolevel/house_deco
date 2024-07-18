package com.movieland.domain.entity.user.image

import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class UserImageFinder(
    private val repository: UserImageRepository
) {

    @Throws(DataNotFoundException::class)
    fun findById(id: Long): UserImage {
        return repository.findByIdOrNull(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_IMAGE_IS_NOT_FOUND,
            message = "유저 이미지를 찾을 수 없습니다."
        )
    }

    @Throws(DataNotFoundException::class)
    fun findByIdAndUserId(id: Long, userId: Long): UserImage {
        return repository.findByIdAndUserId(id, userId) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_IMAGE_IS_NOT_FOUND,
            message = "유저 이미지를 찾을 수 없습니다."
        )
    }
}

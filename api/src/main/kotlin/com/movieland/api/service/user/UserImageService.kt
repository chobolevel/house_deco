package com.movieland.api.service.user

import com.movieland.api.dto.user.image.CreateUserProfileImageRequestDto
import com.movieland.api.dto.user.image.UpdateUserProfileImageRequestDto
import com.movieland.api.service.user.converter.UserImageConverter
import com.movieland.api.service.user.updater.UserImageUpdatable
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.image.UserImageFinder
import com.movieland.domain.entity.user.image.UserImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserImageService(
    private val repository: UserImageRepository,
    private val finder: UserImageFinder,
    private val converter: UserImageConverter,
    private val updaters: List<UserImageUpdatable>,
    private val userFinder: UserFinder
) {

    fun createUserProfileImage(userId: Long, request: CreateUserProfileImageRequestDto): Long {
        val user = userFinder.findById(userId)
        val userImage = converter.convert(request).also {
            it.setBy(user)
        }
        return repository.save(userImage).id!!
    }

    fun updateUserProfileImage(id: Long, userId: Long, request: UpdateUserProfileImageRequestDto): Long {
        val userImage = finder.findByIdAndUserId(id, userId)
        updaters.forEach { it.markAsUpdate(request, userImage) }
        return userImage.id!!
    }

    fun deleteUserProfileImage(id: Long, userId: Long): Boolean {
        val userImage = finder.findByIdAndUserId(id, userId)
        userImage.delete()
        return true
    }
}

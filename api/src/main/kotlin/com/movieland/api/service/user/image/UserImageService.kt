package com.movieland.api.service.user.image

import com.movieland.api.dto.user.image.CreateUserProfileImageRequestDto
import com.movieland.api.dto.user.image.UpdateUserProfileImageRequestDto
import com.movieland.api.service.user.image.convert.UserImageConverter
import com.movieland.api.service.user.image.updater.UserImageUpdatable
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

    fun createUserProfileImage(userId: String, request: CreateUserProfileImageRequestDto): String {
        val user = userFinder.findById(userId)
        val userImage = converter.convert(request).also {
            it.setBy(user)
        }
        return repository.save(userImage).id!!
    }

    fun updateUserProfileImage(id: String, userId: String, request: UpdateUserProfileImageRequestDto): String {
        val userImage = finder.findByIdAndUserId(id, userId)
        updaters.forEach { it.markAsUpdate(request, userImage) }
        return userImage.id!!
    }

    fun deleteUserProfileImage(id: String, userId: String): Boolean {
        val userImage = finder.findByIdAndUserId(id, userId)
        userImage.delete()
        return true
    }

}

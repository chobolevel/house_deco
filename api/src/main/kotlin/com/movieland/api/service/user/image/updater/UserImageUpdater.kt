package com.movieland.api.service.user.image.updater

import com.movieland.api.dto.user.image.UpdateUserProfileImageRequestDto
import com.movieland.domain.entity.user.image.UserImage
import com.movieland.domain.entity.user.image.UserImageUpdateMask
import org.springframework.stereotype.Component

@Component
class UserImageUpdater : UserImageUpdatable {

    override fun markAsUpdate(request: UpdateUserProfileImageRequestDto, userImage: UserImage): UserImage {
        request.updateMask.forEach {
            when (it) {
                UserImageUpdateMask.ORIGIN_URL -> userImage.originUrl = request.originUrl!!
                UserImageUpdateMask.NAME -> userImage.name = request.name!!
            }
        }
        return userImage
    }

    override fun order(): Int {
        return 0
    }
}

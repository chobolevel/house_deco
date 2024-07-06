package com.movieland.api.service.user.point.updater

import com.movieland.api.dto.user.point.UpdateUserPointRequestDto
import com.movieland.domain.entity.user.point.UserPoint
import com.movieland.domain.entity.user.point.UserPointUpdateMask
import org.springframework.stereotype.Component

@Component
class UserPointUpdater : UserPointUpdatable {

    override fun markAsUpdate(request: UpdateUserPointRequestDto, userPoint: UserPoint): UserPoint {
        request.updateMask.forEach {
            when (it) {
                UserPointUpdateMask.CURRENCY -> {
                    val user = userPoint.user!!
                    user.point -= userPoint.currency
                    userPoint.currency = request.currency!!
                    user.point += request.currency
                }

                UserPointUpdateMask.TYPE -> userPoint.type = request.type!!
                UserPointUpdateMask.DESCRIPTION -> userPoint.description = request.description!!
            }
        }
        return userPoint
    }

    override fun order(): Int {
        return 0
    }
}

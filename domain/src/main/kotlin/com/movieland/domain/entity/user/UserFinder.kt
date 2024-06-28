package com.movieland.domain.entity.user

import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.QUser.user
import com.movieland.domain.exception.DataNotFoundException
import com.movieland.domain.exception.ErrorCode
import com.querydsl.core.types.OrderSpecifier
import org.springframework.stereotype.Component

@Component
class UserFinder(
    private val userRepository: UserRepository,
    private val userCustomRepository: UserCustomRepository
) {

    fun findById(id: String): User {
        return userCustomRepository.findById(id) ?: throw DataNotFoundException(
            errorCode = ErrorCode.USER_IS_NOT_FOUND,
            message = "회원 정보를 찾을 수 없습니다."
        )
    }

    fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmailAndDeletedIsFalse(email)
    }

    fun existsByNickname(nickname: String): Boolean {
        return userRepository.existsByNicknameAndDeletedIsFalse(nickname)
    }

    fun existsByPhone(phone: String): Boolean {
        return userRepository.existsByPhoneAndDeletedIsFalse(phone)
    }

    fun searchByPredicates(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>?
    ): List<User> {
        val orderSpecifiers = orderSpecifiers(orderTypes ?: emptyList())
        return userCustomRepository.findByPredicates(queryFilter.toPredicates(), pagination, orderSpecifiers)
    }

    fun countByPredicates(queryFilter: UserQueryFilter): Long {
        return userCustomRepository.countByPredicates(queryFilter.toPredicates())
    }

    fun orderSpecifiers(orderTypes: List<UserOrderType>): List<OrderSpecifier<*>> {
        return orderTypes.map {
            when (it) {
                UserOrderType.CREATED_AT_ASC -> user.createdAt.asc()
                UserOrderType.CREATED_AT_DESC -> user.createdAt.desc()
            }
        }
    }
}

package com.movieland.api.service.user

import com.movieland.api.dto.common.PaginationResponse
import com.movieland.api.dto.jwt.JwtResponse
import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.api.dto.user.LoginRequestDto
import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.api.dto.user.UserResponseDto
import com.movieland.api.security.TokenProvider
import com.movieland.api.service.user.converter.UserConverter
import com.movieland.api.service.user.updater.UserUpdatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserLoginType
import com.movieland.domain.entity.user.UserOrderType
import com.movieland.domain.entity.user.UserQueryFilter
import com.movieland.domain.entity.user.UserRepository
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userFinder: UserFinder,
    private val authenticationManager: UserAuthenticationManager,
    private val userConverter: UserConverter,
    private val userUpdaters: MutableList<out UserUpdatable>,
    private val tokenProvider: TokenProvider
) {

    @Throws(ParameterInvalidException::class)
    fun createUser(request: CreateUserRequestDto): String {
        val emailExists = userFinder.existsByEmail(request.email)
        val nicknameExists = userFinder.existsByNickname(request.nickname)
        val phoneExists = userFinder.existsByPhone(request.phone)
        if (emailExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.EMAIL_ALREADY_EXISTS,
                message = "이미 존재하는 이메일입니다."
            )
        }
        if (nicknameExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.NICKNAME_ALREADY_EXISTS,
                message = "이미 존재하는 닉네임입니다."
            )
        }
        if (phoneExists) {
            throw ParameterInvalidException(
                errorCode = ErrorCode.PHONE_ALREADY_EXISTS,
                message = "이미 존재하는 전화번호입니다."
            )
        }
        val user = userConverter.convert(request)
        return userRepository.save(user).id!!
    }

    fun login(request: LoginRequestDto): JwtResponse {
        // 로그인 타입으로 구분(GENERAL, KAKAO 등)
        // Authentication 객체 생성(email, password)
        val authenticationToken = if (request.loginType != UserLoginType.GENERAL) {
            UsernamePasswordAuthenticationToken("", "")
        } else {
            val combine = "${request.email},${request.loginType.name}"
            UsernamePasswordAuthenticationToken(combine, request.password)
        }
        // authentication manager 인증 수행
        val authentication = authenticationManager.authenticate(authenticationToken)
        // 인증된 정보 바탕으로 토큰 생성 후 반환
        val result = tokenProvider.generateToken(authentication)
        return result
    }

    fun searchUser(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>?
    ): PaginationResponse {
        val result = userFinder.searchByPredicates(queryFilter, pagination, orderTypes)
        val totalCount = userFinder.countByPredicates(queryFilter)
        return PaginationResponse(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = result.map { userConverter.convert(it) },
            totalCount = totalCount
        )
    }

    fun fetchUser(id: String): UserResponseDto {
        val user = userFinder.findById(id)
        return userConverter.convert(user)
    }

    fun updateUser(id: String, request: UpdateUserRequestDto): String {
        val user = userFinder.findById(id)
        userUpdaters.forEach { it.markAsUpdate(request, user) }
        return user.id!!
    }

    fun deleteUser(id: String): Boolean {
        val user = userFinder.findById(id)
        user.resign()
        return true
    }
}

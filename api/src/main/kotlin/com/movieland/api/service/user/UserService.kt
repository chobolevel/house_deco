package com.movieland.api.service.user

import com.movieland.api.dto.common.PaginationResponseDto
import com.movieland.api.dto.jwt.JwtResponse
import com.movieland.api.dto.user.CreateUserRequestDto
import com.movieland.api.dto.user.LoginRequestDto
import com.movieland.api.dto.user.ReissueRequestDto
import com.movieland.api.dto.user.UpdateUserRequestDto
import com.movieland.api.dto.user.UserResponseDto
import com.movieland.api.security.TokenProvider
import com.movieland.api.service.user.converter.UserConverter
import com.movieland.api.service.user.updater.UserUpdatable
import com.movieland.api.service.user.validator.CreateUserValidatable
import com.movieland.api.service.user.validator.UpdateUserValidatable
import com.movieland.domain.Pagination
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserLoginType
import com.movieland.domain.entity.user.UserOrderType
import com.movieland.domain.entity.user.UserQueryFilter
import com.movieland.domain.entity.user.UserRepository
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.ParameterInvalidException
import com.movieland.domain.exception.PolicyException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userFinder: UserFinder,
    private val createValidators: List<CreateUserValidatable>,
    private val updateValidators: List<UpdateUserValidatable>,
    private val authenticationManager: UserAuthenticationManager,
    private val userConverter: UserConverter,
    private val userUpdaters: List<UserUpdatable>,
    private val tokenProvider: TokenProvider,
    private val refreshTokenService: RefreshTokenService
) {

    @Throws(ParameterInvalidException::class)
    fun createUser(request: CreateUserRequestDto): Long {
        createValidators.forEach { it.validate(request) }
        val user = userConverter.convert(request)
        return userRepository.save(user).id!!
    }

    fun login(request: LoginRequestDto): JwtResponse {
        val authenticationToken = if (request.loginType != UserLoginType.GENERAL) {
            // social login
            val combine = "${request.email},${request.loginType}"
            val password = request.socialId
            UsernamePasswordAuthenticationToken(combine, password)
        } else {
            // general login
            val combine = "${request.email},${request.loginType.name}"
            UsernamePasswordAuthenticationToken(combine, request.password)
        }
        val authentication = authenticationManager.authenticate(authenticationToken)
        val result = tokenProvider.generateToken(authentication).also {
            refreshTokenService.setUserRefreshToken(authentication.name, it.refreshToken)
        }
        return result
    }

    fun reissue(request: ReissueRequestDto): JwtResponse {
        tokenProvider.validateToken(request.refreshToken)
        val authentication = tokenProvider.getAuthentication(request.refreshToken) ?: throw PolicyException(
            errorCode = ErrorCode.INVALID_TOKEN,
            status = HttpStatus.UNAUTHORIZED,
            message = "토근이 만료되었습니다. 재로그인 해주세요."
        )
        return tokenProvider.generateToken(authentication).also {
            refreshTokenService.setUserRefreshToken(authentication.name, it.refreshToken)
        }
    }

    fun searchUser(
        queryFilter: UserQueryFilter,
        pagination: Pagination,
        orderTypes: List<UserOrderType>?
    ): PaginationResponseDto {
        val result = userFinder.searchByPredicates(queryFilter, pagination, orderTypes)
        val totalCount = userFinder.countByPredicates(queryFilter)
        return PaginationResponseDto(
            skipCount = pagination.skip,
            limitCount = pagination.limit,
            data = result.map { userConverter.convert(it) },
            totalCount = totalCount
        )
    }

    fun fetchUser(id: Long): UserResponseDto {
        val user = userFinder.findById(id)
        return userConverter.convert(user)
    }

    fun updateUser(id: Long, request: UpdateUserRequestDto): Long {
        updateValidators.forEach { it.validate(request) }
        val user = userFinder.findById(id)
        userUpdaters.sortedBy { it.order() }.forEach { it.markAsUpdate(request, user) }
        return user.id!!
    }

    fun deleteUser(id: Long): Boolean {
        val user = userFinder.findById(id)
        user.resign()
        return true
    }
}

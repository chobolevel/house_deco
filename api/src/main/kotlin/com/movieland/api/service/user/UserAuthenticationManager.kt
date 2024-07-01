package com.movieland.api.service.user

import com.movieland.api.security.UserDetailsImpl
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.entity.user.UserLoginType
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.UnAuthorizedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserAuthenticationManager(
    private val userFinder: UserFinder,
    private val passwordEncoder: BCryptPasswordEncoder
) : AuthenticationManager {

    @Throws(UnAuthorizedException::class, BadCredentialsException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val combine = authentication.name.split(",")
        val email = combine[0]
        val loginType = UserLoginType.find(combine[1]) ?: throw UnAuthorizedException(
            errorCode = ErrorCode.INVALID_USER_LOGIN_TYPE,
            message = "유효하지 않은 유저 로그인 타입입니다."
        )
        val password = authentication.credentials.toString()
        val findUser = userFinder.findByEmailAndLoginType(email, loginType)
        val passwordMatched = passwordEncoder.matches(password, findUser.password)
        if (!passwordMatched) {
            throw BadCredentialsException("아이디 또는 비밀번호가 올바르지 않습니다.")
        }
        val authorities = AuthorityUtils.createAuthorityList(findUser.role.name)
        return UsernamePasswordAuthenticationToken(findUser.id, findUser.password, authorities)
    }
}

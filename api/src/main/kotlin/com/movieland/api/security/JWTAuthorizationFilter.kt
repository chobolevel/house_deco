package com.movieland.api.security

import com.movieland.api.service.user.UserAuthenticationManager
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException

class JWTAuthorizationFilter(
    private val userAuthenticationManager: UserAuthenticationManager,
    private val tokenProvider: TokenProvider,
) : BasicAuthenticationFilter(userAuthenticationManager) {

    private val TOKEN_PREFIX = "Bearer "

    @Throws(IOException::class, ServletException::class, AccessDeniedException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        // session 사용하지 않기 때문에 context holder 인증 객체 있다면 넣어 주는 작업 수행
        // 토큰 없는 경우 다음 핊터 수행
        val header = request.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }
        tokenProvider.getAuthentication(header.substring(TOKEN_PREFIX.length)).also {
            SecurityContextHolder.getContext().authentication = it
        }
        chain.doFilter(request, response)
    }
}

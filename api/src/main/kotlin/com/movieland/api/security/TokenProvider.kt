package com.movieland.api.security

import com.movieland.api.dto.jwt.JwtResponse
import com.movieland.api.properties.JwtProperties
import com.movieland.domain.entity.user.UserFinder
import com.movieland.domain.exception.ErrorCode
import com.movieland.domain.exception.PolicyException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.TimeUnit

@Component
class TokenProvider(
    private val jwtProperties: JwtProperties,
    private val userFinder: UserFinder
) {
    // generateToken, getAuthentication, validateToken
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    fun generateToken(authentication: Authentication): JwtResponse {
        val now = Date()
        val accessExpiration = Date(now.time + TimeUnit.HOURS.toMillis(1))
        val refreshExpiration = Date(now.time + TimeUnit.DAYS.toMillis(30))
        val authorities = authentication.authorities.map { auth -> auth.authority.toString() }
        val accessToken = generateAccessToken(
            authentication = authentication,
            authorities = authorities,
            issuedAt = now,
            expiration = accessExpiration
        )
        val refreshToken = generateRefreshToken(
            authentication = authentication,
            authorities = authorities,
            issuedAt = now,
            expiration = refreshExpiration
        )
        return JwtResponse(accessToken, refreshToken)
    }

    fun generateAccessToken(
        authentication: Authentication,
        authorities: List<String>,
        issuedAt: Date,
        expiration: Date
    ): String {
        val accessToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .setSubject(authentication.name)
            .claim("authorities", authorities)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
        return "Bearer $accessToken"
    }

    fun generateRefreshToken(
        authentication: Authentication,
        authorities: List<String>,
        issuedAt: Date,
        expiration: Date
    ): String {
        val refreshToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .setSubject(authentication.name)
            .claim("authorities", authorities)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
        return "Bearer $refreshToken"
    }

    @Throws(PolicyException::class)
    fun validateToken(token: String): Boolean {
        return try {
            Jwts
                .parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
            true
        } catch (e: ExpiredJwtException) {
            // 토큰 시간 만료
            throw PolicyException(
                errorCode = ErrorCode.EXPIRED_TOKEN,
                message = "토큰이 만료되었습니다."
            )
        } catch (e: JwtException) {
            // 토큰 검증 불가능
            throw PolicyException(
                errorCode = ErrorCode.INVALID_TOKEN,
                message = "유효하지 않은 토큰입니다."
            )
        }
    }

    fun getAuthentication(token: String): Authentication? {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
                .body
            val findUser = userFinder.findByEmail(claims.subject)
            val userDetails = UserDetailsImpl(findUser)
            return UsernamePasswordAuthenticationToken(userDetails, token, userDetails.authorities)
        } catch (e: Exception) {
            logger.warn("Token is invalid", e)
            null
        }
    }
}

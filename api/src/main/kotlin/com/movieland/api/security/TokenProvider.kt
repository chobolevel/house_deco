package com.movieland.api.security

import com.movieland.api.dto.jwt.JwtResponse
import com.movieland.api.properties.JwtProperties
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.TimeUnit

@Component
class TokenProvider(
    private val jwtProperties: JwtProperties
) {

    fun generateToken(authentication: Authentication): JwtResponse {
        // TODO access_token, refresh_token 로직 분리
        // TODO refresh_token 저장?
        val now = Date()
        val expiration = Date(now.time + TimeUnit.HOURS.toMillis(1))
        val refreshExpiration = Date(now.time + TimeUnit.DAYS.toMillis(30))
        val authClaims: MutableList<String> = mutableListOf()
        authentication.authorities?.let { authorities ->
            authorities.forEach { claim -> authClaims.add(claim.toString()) }
        }
        val accessToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .setSubject(authentication.name)
            .claim("roles", authClaims)
            .signWith(SignatureAlgorithm.HS512, jwtProperties.secretKey)
            .compact()
        val refreshToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(now)
            .setExpiration(refreshExpiration)
            .setSubject(authentication.name)
            .claim("roles", authClaims)
            .signWith(SignatureAlgorithm.HS512, jwtProperties.secretKey)
            .compact()
        return JwtResponse(accessToken, refreshToken)
    }

}

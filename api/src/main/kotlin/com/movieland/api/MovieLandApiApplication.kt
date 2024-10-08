package com.movieland.api

import com.movieland.api.properties.JwtProperties
import com.movieland.domain.DomainConfigurationLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackages = ["com.movieland.domain"])
@EnableJpaRepositories(basePackages = ["com.movieland.domain"])
@SpringBootApplication
@Import(DomainConfigurationLoader::class)
@EnableConfigurationProperties(value = [JwtProperties::class])
class MovieLandApiApplication

fun main(args: Array<String>) {
    runApplication<MovieLandApiApplication>(*args)
}

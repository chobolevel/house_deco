package com.movieland.api

import com.movieland.domain.DomainConfigurationLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(DomainConfigurationLoader::class)
class MovieLandApiApplication

fun main(args: Array<String>) {
  runApplication<MovieLandApiApplication>(*args)
}

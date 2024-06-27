package com.movieland.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieLandApiApplication

fun main(args: Array<String>) {
  runApplication<MovieLandApiApplication>(*args)
}

package com.chobolevel.movieland

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovielandApplication

fun main(args: Array<String>) {
    runApplication<MovielandApplication>(*args)
}

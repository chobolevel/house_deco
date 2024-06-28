package com.movieland.domain.config

import com.github.f4b6a3.tsid.TsidFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TsidConfiguration {

    @Bean
    fun tsidFactory(): TsidFactory {
        return TsidFactory()
    }
}

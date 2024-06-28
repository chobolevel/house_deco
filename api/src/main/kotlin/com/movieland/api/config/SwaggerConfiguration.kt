package com.movieland.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(apiInfo())
    }

    private fun apiInfo(): Info {
        return Info()
            .title("Movie Land Api 명세")
            .description("Movie Land 서비스의 API 명세 정보")
            .version("1.0.0")
    }
}

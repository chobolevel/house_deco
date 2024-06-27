package com.movieland.domain.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
// for jpa auditing
@EnableJpaAuditing
class AuditConfiguration {
}

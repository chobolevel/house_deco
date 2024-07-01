package com.movieland.api.annorations

import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("hasRole('ADMIN')")
annotation class HasAuthorityAdmin()

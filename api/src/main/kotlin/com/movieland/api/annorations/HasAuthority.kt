package com.movieland.api.annorations

import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
annotation class HasAuthority

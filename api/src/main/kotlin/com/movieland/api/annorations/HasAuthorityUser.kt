package com.movieland.api.annorations

import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("hasRole('USER')")
annotation class HasAuthorityUser()

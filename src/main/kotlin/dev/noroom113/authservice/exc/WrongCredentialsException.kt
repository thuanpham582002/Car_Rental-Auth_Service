package dev.noroom113.authservice.exc

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class WrongCredentialsException(message: String?) : RuntimeException(message)

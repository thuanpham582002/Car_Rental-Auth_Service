package dev.noroom113.authservice.exc

import org.springframework.http.HttpStatus

data class GenericErrorResponse(override val message: String, private val httpStatus: HttpStatus) :
    RuntimeException(message)

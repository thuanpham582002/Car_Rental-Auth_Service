package dev.noroom113.authservice.request

data class LoginRequest(
    private val username: String,
    private val password: String,
)

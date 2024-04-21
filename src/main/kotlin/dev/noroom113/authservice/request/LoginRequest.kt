package dev.noroom113.authservice.request

data class LoginRequest(
    val username: String,
    val password: String,
)

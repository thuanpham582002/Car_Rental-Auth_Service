package dev.noroom113.authservice.request

data class RegisterRequest(
    val identityNumber: String,
    val username: String,
    val email: String,
    val password: String,
)

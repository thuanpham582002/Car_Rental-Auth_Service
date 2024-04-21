package dev.noroom113.authservice.dto

data class RegisterRequestDto(
    val id: Long,
    val identityId: String,
    val username: String,
    val email: String,
)
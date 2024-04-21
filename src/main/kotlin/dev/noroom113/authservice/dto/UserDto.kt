package dev.noroom113.authservice.dto

import dev.noroom113.authservice.enums.Accessibility

data class UserDto(
    val id: String,
    val username: String,
    val password: String,
    val accessibility: Accessibility,
)

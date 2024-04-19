package dev.noroom113.authservice.dto

import dev.noroom113.authservice.enums.Role

data class UserDto(
    private val id: String,
    private val username: String,
    private val password: String,
    private val role: Role,
)

package dev.noroom113.authservice.request

import java.io.Serializable

data class LoginRequest(
    val identityId: String,
    val password: String,
) : Serializable

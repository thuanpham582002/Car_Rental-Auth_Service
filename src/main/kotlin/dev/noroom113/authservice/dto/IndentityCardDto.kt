package dev.noroom113.authservice.dto

import java.io.Serializable
import java.sql.Date

data class IndentityCardDto(
    val id: Long,
    val number: String,
) : Serializable
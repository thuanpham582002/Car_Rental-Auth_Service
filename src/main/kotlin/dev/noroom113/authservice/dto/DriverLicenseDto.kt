package dev.noroom113.authservice.dto

import java.io.Serializable
import java.sql.Date

data class DriverLicenseDto(
    val id: Long,
    val number: String,
    val imageBase64: String? = null,
) : Serializable
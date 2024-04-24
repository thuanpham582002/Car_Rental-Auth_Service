package dev.noroom113.authservice.dto

import java.io.Serializable

data class DriverLicenseDto(
    val id: Long,
    val number: String,
) : Serializable
package dev.noroom113.authservice.dto

import java.io.Serializable

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val accessibilityIds: List<Long>,
    val indentityCard: IndentityCardDto,
    val driverLicense: DriverLicenseDto? = null,
) : Serializable
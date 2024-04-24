package dev.noroom113.authservice.service

import dev.noroom113.authservice.client.UserServiceClient
import dev.noroom113.authservice.dto.TokenDto
import dev.noroom113.authservice.dto.UserDto
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userServiceClient: UserServiceClient,
    private val jwtService: JwtService,
) {
    fun login(request: LoginRequest, tokenReceive: (String) -> Unit): UserDto {
        val userDto = userServiceClient.login(request).body ?: throw Exception("Wrong credentials")
        tokenReceive(jwtService.generateToken(userDto))
        return userDto
    }

    fun register(request: RegisterRequest): UserDto? {
        return userServiceClient.save(request).body
    }

    fun updateToken(indentityCardName: String): TokenDto? {
        val userDto = userServiceClient.getUserByIndentityCardName(indentityCardName).body ?: throw Exception("User not found")
        return TokenDto(jwtService.generateToken(userDto))
    }
}

package dev.noroom113.authservice.service

import dev.noroom113.authservice.client.UserServiceClient
import dev.noroom113.authservice.dto.TokenDto
import dev.noroom113.authservice.dto.UserDto
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userServiceClient: UserServiceClient,
    private val jwtService: JwtService,
) {
    fun login(request: LoginRequest): TokenDto {
        val userDto = userServiceClient.login(request).body ?: throw Exception("Wrong credentials")
        return TokenDto(jwtService.generateToken(userDto))
    }

    fun register(request: RegisterRequest): UserDto? {
        return userServiceClient.save(request).body
    }
}

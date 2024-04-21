package dev.noroom113.authservice.service

import dev.noroom113.authservice.client.UserServiceClient
import dev.noroom113.authservice.dto.RegisterDto
import dev.noroom113.authservice.dto.TokenDto
import dev.noroom113.authservice.exc.WrongCredentialsException
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userServiceClient: UserServiceClient,
    private val jwtService: JwtService,
) {
    fun login(request: LoginRequest): TokenDto {
        val authenticate: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )
        if (authenticate.isAuthenticated) return TokenDto(jwtService.generateToken(request.username))
        else throw WrongCredentialsException("Wrong credentials")
    }

    fun register(request: RegisterRequest): RegisterDto? {
        return userServiceClient.save(request).body
    }
}

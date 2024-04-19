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
                request.getUsername(),
                request.getPassword()
            )
        )
        if (authenticate.isAuthenticated) return TokenDto
            .builder()
            .token(jwtService!!.generateToken(request.getUsername()))
            .build()
        else throw WrongCredentialsException("Wrong credentials")
    }

    fun register(request: RegisterRequest?): RegisterDto {
        return userServiceClient.save(request).getBody()
    }
}

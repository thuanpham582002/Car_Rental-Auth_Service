package dev.noroom113.authservice.controller

import dev.noroom113.authservice.dto.RegisterDto
import dev.noroom113.authservice.dto.TokenDto
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import dev.noroom113.authservice.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<TokenDto> {
        return ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<RegisterDto> {
        return ResponseEntity.ok(authService.register(request))
    }
}

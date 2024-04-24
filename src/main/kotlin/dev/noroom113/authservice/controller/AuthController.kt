package dev.noroom113.authservice.controller

import dev.noroom113.authservice.dto.IndentityCardDto
import dev.noroom113.authservice.dto.TokenDto
import dev.noroom113.authservice.dto.UserDto
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import dev.noroom113.authservice.service.AuthService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<UserDto> {
        return ResponseEntity.ok(authService.login(request) {
            httpServletResponse.setHeader("Authorization", "Bearer $it")
        })
    }

    fun updateToken(@RequestBody indentityCardName : String): ResponseEntity<TokenDto> {
        return ResponseEntity.ok(authService.updateToken(indentityCardName))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<UserDto> {
        return ResponseEntity.ok(authService.register(request))
    }
}

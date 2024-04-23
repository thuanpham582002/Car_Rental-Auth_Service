package dev.noroom113.authservice.client

import dev.noroom113.authservice.dto.UserDto
import dev.noroom113.authservice.request.LoginRequest
import dev.noroom113.authservice.request.RegisterRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "user-service", path = "/api/v1/user")
interface UserServiceClient {
    @PostMapping("/save")
    fun save(@RequestBody request: RegisterRequest): ResponseEntity<UserDto>

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<UserDto>

    @GetMapping("/getUserByIndentityCardName/{indentityCardName}")
    fun getUserByIndentityCardName(@PathVariable indentityCardName: String): ResponseEntity<UserDto>
}

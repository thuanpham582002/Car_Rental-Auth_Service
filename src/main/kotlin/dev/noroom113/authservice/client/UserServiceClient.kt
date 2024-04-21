package dev.noroom113.authservice.client

import dev.noroom113.authservice.dto.RegisterDto
import dev.noroom113.authservice.dto.UserDto
import dev.noroom113.authservice.request.RegisterRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "user-service", path = "/v1/user")
interface UserServiceClient {
    @PostMapping("/save")
    fun save(@RequestBody request: RegisterRequest): ResponseEntity<RegisterDto?>

    @GetMapping("/getUserByUsername/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<UserDto?>
}

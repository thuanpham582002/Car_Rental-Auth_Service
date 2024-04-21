package dev.noroom113.authservice.service

import dev.noroom113.authservice.dto.UserDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors
import java.util.stream.Stream

class CustomUserDetails(
    private val user: UserDto
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Stream.of(user.accessibility)
            .map { role -> SimpleGrantedAuthority(role.name) }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

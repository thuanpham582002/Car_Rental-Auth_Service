package dev.noroom113.authservice.service


import dev.noroom113.authservice.client.UserServiceClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userServiceClient: UserServiceClient
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Unit = checkNotNull(userServiceClient.getUserByUsername(username).getBody())
        return CustomUserDetails(user)
    }
}
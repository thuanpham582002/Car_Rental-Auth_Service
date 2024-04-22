package dev.noroom113.authservice.service

import dev.noroom113.authservice.dto.UserDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtService {
    fun generateToken(username: UserDto): String {
        return Jwts.builder()
            .setClaims(mapOf("accessibilityIds" to username.accessibilityIds))
            .setSubject(username.indentityCard.number)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(signKey, SignatureAlgorithm.HS256).compact().apply {
                println("Generated token: $this")
            }
    }

    private val signKey: Key
        get() {
            val keyBytes: ByteArray = Decoders.BASE64.decode(SECRET)
            return Keys.hmacShaKeyFor(keyBytes)
        }

    companion object {
        const val SECRET: String = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"
    }
}

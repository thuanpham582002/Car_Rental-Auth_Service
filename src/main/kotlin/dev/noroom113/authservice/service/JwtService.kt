package dev.noroom113.authservice.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtService(
    private val customUserDetailsService: CustomUserDetailsService
){
    fun generateToken(username: String): String {
        val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(username)
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails)
    }

    private fun createToken(claims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuer(userDetails.authorities.iterator().next().authority)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(signKey, SignatureAlgorithm.HS256).compact()
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

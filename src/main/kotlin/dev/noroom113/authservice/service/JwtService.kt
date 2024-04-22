package dev.noroom113.authservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import dev.noroom113.authservice.client.Accessibility
import dev.noroom113.authservice.client.UrlAccessable
import dev.noroom113.authservice.dto.UserDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtService(
    @Qualifier(value = "accessibilities") private val accessibilities: List<Accessibility>,
) {
    fun generateToken(username: UserDto): String {
        val accessibilityIds = username.accessibilityIds
        val setUrlsAccessable = mutableSetOf<UrlAccessable>()

        accessibilities
            .filter { accessibilityIds.contains(it.id) }
            .forEach {
                setUrlsAccessable.addAll(getUrlsAccessable(it))
            }
        return Jwts.builder()
            .setClaims(mapOf("urlsAccessable" to ObjectMapper().writeValueAsString(setUrlsAccessable)))
            .setSubject(username.indentityCard.number)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(signKey, SignatureAlgorithm.HS256).compact().apply {
                println("Generated token: $this")
            }
    }

    private fun getUrlsAccessable(accessibility: Accessibility): Set<UrlAccessable> {
        val urlsAccessable = mutableSetOf<UrlAccessable>()
        accessibility.urlAccessables.forEach {
            urlsAccessable.add(it)
        }
        accessibility.childAccessibilities.forEach {
            urlsAccessable.addAll(getUrlsAccessable(it))
        }
        return urlsAccessable
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

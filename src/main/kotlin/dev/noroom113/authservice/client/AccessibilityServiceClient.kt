package dev.noroom113.authservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import java.io.Serializable

@FeignClient(name = "accessibility-service", path = "/api/v1/accessibility")
interface AccessibilityServiceClient {

    @GetMapping("/all")
    fun getAccessibilities(): ResponseEntity<List<Accessibility>>
}

@Configuration
class AccessibilityResponseInterceptor(
    private val accessibilityServiceClient: AccessibilityServiceClient,
) {
    private var accessibilities: List<Accessibility> = emptyList()

    @Bean("accessibilities")
    fun getAccessibilities(): List<Accessibility> {
        return accessibilities
    }

    @Scheduled(fixedRate = 5000)
    fun getAccessibilitiesByScheduled() {
        accessibilityServiceClient.getAccessibilities().body?.let {
            accessibilities = it
        }
        println(accessibilities)
    }
}

data class Accessibility(
    val id: Long = 0,
    val name: String,
    val description: String,
    val urlAccessables: Set<UrlAccessable> = emptySet(),
    val childAccessibilities: Set<Accessibility> = emptySet(),
) {
    override fun toString(): String {
        val urlAccessables = urlAccessables.joinToString { it.toString() }
        val childAccessibilities = childAccessibilities.joinToString { it.toString() }
        return "$id $name $description $urlAccessables $childAccessibilities"
    }
}

data class UrlAccessable(
    val method: Set<HttpMethod>,
    val uri: String,
) : Serializable

enum class HttpMethod {
    GET, POST, PUT, DELETE, ALL
}

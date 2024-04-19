package dev.noroom113.authservice.config

import dev.noroom113.authservice.client.CustomErrorDecoder
import feign.codec.ErrorDecoder

@org.springframework.context.annotation.Configuration
class FeignConfig {
    @org.springframework.context.annotation.Bean
    fun errorDecoder(): ErrorDecoder {
        return CustomErrorDecoder()
    }
}
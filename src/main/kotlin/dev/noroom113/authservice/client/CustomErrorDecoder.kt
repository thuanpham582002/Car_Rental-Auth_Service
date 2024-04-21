package dev.noroom113.authservice.client

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder

class CustomErrorDecoder : ErrorDecoder {
    private val mapper = ObjectMapper()

    override fun decode(methodKey: String, response: Response): Exception {
        return when (response.status()) {
            else -> Exception(response.reason())
        }
    }
}
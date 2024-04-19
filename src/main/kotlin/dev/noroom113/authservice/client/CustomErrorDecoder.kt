package dev.noroom113.authservice.client

import com.fasterxml.jackson.databind.ObjectMapper
import dev.noroom113.authservice.exc.GenericErrorResponse
import dev.noroom113.authservice.exc.ValidationException
import feign.Response
import feign.codec.ErrorDecoder
import org.apache.commons.io.IOUtils
import org.springframework.http.HttpStatus
import java.io.IOException
import java.nio.charset.StandardCharsets

class CustomErrorDecoder : ErrorDecoder {
    private val mapper = ObjectMapper()

    override fun decode(methodKey: String, response: Response): Exception {
        try {
            response.body().asInputStream().use { body ->
                val errors: Map<String, String> =
                    mapper.readValue<Map<*, *>>(IOUtils.toString(body, StandardCharsets.UTF_8), MutableMap::class.java)
                return if (response.status() == 400) {
                    ValidationException.builder()
                        .validationErrors(errors).build()
                } else GenericErrorResponse
                    .builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message(errors["error"])
                    .build()
            }
        } catch (exception: IOException) {
            throw GenericErrorResponse.builder()
                .httpStatus(HttpStatus.valueOf(response.status()))
                .message(exception.message)
                .build()
        }
    }
}
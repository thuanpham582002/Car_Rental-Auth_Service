package dev.noroom113.authservice.exc


class ValidationException : RuntimeException() {
    private val validationErrors: Map<String, String>? = null
}
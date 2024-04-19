package dev.noroom113.authservice.exc

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GeneralExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception): ResponseEntity<*> {
        val errors: MutableMap<String, String?> = HashMap()
        errors["error"] = ex.message
        return ResponseEntity<Map<String, String?>>(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(GenericErrorResponse::class)
    fun genericError(exception: GenericErrorResponse): ResponseEntity<*> {
        val errors: MutableMap<String, String?> = HashMap()
        errors["error"] = exception.message
        return ResponseEntity<Any?>(errors, exception.getHttpStatus())
    }

    @ExceptionHandler(WrongCredentialsException::class)
    fun usernameOrPasswordInvalidException(exception: WrongCredentialsException): ResponseEntity<*> {
        val errors: MutableMap<String, String?> = HashMap()
        errors["error"] = exception.message
        return ResponseEntity<Map<String, String?>>(errors, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ValidationException::class)
    fun validationException(exception: ValidationException): ResponseEntity<*> {
        return ResponseEntity.badRequest().body(exception.getValidationErrors())
    }
}

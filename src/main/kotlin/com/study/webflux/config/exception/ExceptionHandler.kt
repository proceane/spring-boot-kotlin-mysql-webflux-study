package com.study.webflux.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [CustomException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFound() {
    }
}
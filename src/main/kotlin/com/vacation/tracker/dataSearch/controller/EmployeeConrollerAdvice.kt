package com.vacation.tracker.dataSearch.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpServerErrorException

@ControllerAdvice(basePackageClasses = [EmployeeController::class])
class EmployeeConrollerAdvice {

    @ExceptionHandler(MissingRequestHeaderException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingRequestHeaderException(e: MissingRequestHeaderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInternalServerError(e: MissingRequestHeaderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGlobalException(e: MissingRequestHeaderException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}
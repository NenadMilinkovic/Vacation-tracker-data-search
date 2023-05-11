package com.vacation.tracker.dataSearch.controller

import com.vacation.tracker.dataSearch.dto.LoginDto
import com.vacation.tracker.dataSearch.dto.LoginResponseDto
import com.vacation.tracker.dataSearch.service.EmployeeService
import com.vacation.tracker.dataSearch.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val tokenService: TokenService,
    private val employeeService: EmployeeService
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): ResponseEntity<LoginResponseDto> {
        val user = employeeService.getEmployee(payload.email) ?: throw Exception("Login failed")

        if (payload.password != user.password) {
            throw Exception("Login failed")
        }

        return ResponseEntity(LoginResponseDto(
            token = tokenService.createToken(user),
        ), HttpStatus.OK)
    }

}
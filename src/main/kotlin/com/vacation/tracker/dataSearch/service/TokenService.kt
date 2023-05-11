package com.vacation.tracker.dataSearch.service

import com.vacation.tracker.dataSearch.model.Employee
import com.vacation.tracker.dataSearch.repository.EmployeeRepository
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val employeeRepository: EmployeeRepository
) {
    fun createToken(employee: Employee): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(employee.email)
            .claim("email", employee.email)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): Employee? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val email = jwt.claims["email"] as String
            employeeRepository.findByEmail(email)
        } catch (e: Exception) {
            null
        }
    }
}

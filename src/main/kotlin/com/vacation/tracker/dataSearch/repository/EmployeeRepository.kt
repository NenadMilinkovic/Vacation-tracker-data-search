package com.vacation.tracker.dataSearch.repository

import com.vacation.tracker.dataSearch.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmployeeRepository: JpaRepository<Employee, UUID> {
    fun findByEmail(email: String): Employee?
}
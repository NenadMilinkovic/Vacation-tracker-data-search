package com.vacation.tracker.dataSearch.repository

import com.vacation.tracker.dataSearch.model.Vacation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface VacationRepository: JpaRepository<Vacation, UUID> {

    @Query(
        "SELECT vacation.id, vacation.days, vacation.free_days, vacation.used_days, vacation.year, vacation.employee_id\n" +
                "FROM public.vacation\n" +
                "JOIN public.employee\n" +
                "ON vacation.employee_id = employee.id\n" +
                "WHERE vacation.year = :year and employee.email = :email",
        nativeQuery = true
    )
    fun findByEmployeeEmailAndVacationYear(
        @Param("year") year: Int,
        @Param("email") email: String
    ): Vacation?
}
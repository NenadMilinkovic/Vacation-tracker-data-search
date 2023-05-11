package com.vacation.tracker.dataSearch.repository

import com.vacation.tracker.dataSearch.model.UsedVacation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Date
import java.util.UUID

interface UsedVacationRepository: JpaRepository<UsedVacation, UUID> {
    @Query(
        "select used_vacation.id, used_vacation.spend_days, used_vacation.start_date, used_vacation.end_date, used_vacation.vacation_id \n" +
                "from public.used_vacation\n" +
                "join public.vacation on vacation.id = used_vacation.vacation_id\n" +
                "join public.employee on vacation.employee_id = employee.id\n" +
                "where employee.email = :email and\n" +
                "used_vacation.start_date >= :startDate and \n" +
                "used_vacation.end_date <= :endDate",
        nativeQuery = true
    )
    fun getUsedVacationPeriod(
        @Param("email") email: String,
        @Param("startDate") startDate: Date,
        @Param("endDate") endDate: Date
    ): List<UsedVacation>
}
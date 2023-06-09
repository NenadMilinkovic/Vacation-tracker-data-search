package com.vacation.tracker.dataSearch.service

import com.vacation.tracker.dataSearch.model.Employee
import com.vacation.tracker.dataSearch.model.UsedVacation
import com.vacation.tracker.dataSearch.model.Vacation
import com.vacation.tracker.dataSearch.repository.EmployeeRepository
import com.vacation.tracker.dataSearch.repository.UsedVacationRepository
import com.vacation.tracker.dataSearch.repository.VacationRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class EmployeeService(
    private val vacationRepository: VacationRepository,
    private val usedVacationRepository: UsedVacationRepository,
    private val employeeRepository: EmployeeRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getEmployee(email: String) = employeeRepository.findByEmail(email)

    fun getVacationInfo(year: Int): Vacation {

        val email = (SecurityContextHolder.getContext().authentication.principal as Employee).email

        return vacationRepository.findByEmployeeEmailAndVacationYear(year, email)
            ?: throw Exception("Employee don't have vacation in $year year")
    }

    fun getUsedVacationDaysPerPeriod(startDate: String, endDate: String): List<UsedVacation> {

        val email = (SecurityContextHolder.getContext().authentication.principal as Employee).email
        val dataFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)

        if (dataFormat.parse(startDate) > dataFormat.parse(endDate)) {
            throw Exception("Start date of vacation must be before end date")
        }

        return usedVacationRepository.getUsedVacationPeriod(
            email,
            dataFormat.parse(startDate),
            dataFormat.parse(endDate)
        )
    }

    fun addUsedVacationDays(usedVacation: UsedVacation) {

        if (usedVacation.startDate > usedVacation.endDate) {
            throw Exception("Start date of vacation must be before end date")
        }

        val email = (SecurityContextHolder.getContext().authentication.principal as Employee).email
        val calendar = Calendar.getInstance()
        calendar.time = usedVacation.startDate
        val year = calendar.get(Calendar.YEAR)

        val vacation = vacationRepository.findByEmployeeEmailAndVacationYear(year, email)
            ?: throw Exception("Employee don't have vacation in $year year")

        usedVacation.vacation = vacation

        if (vacation.freeDays - usedVacation.spendDays < 0) {
            logger.error("Employee $email don't have enough free days")
            throw Exception("Employee $email don't have enough free days")
        }

        usedVacationRepository.save(usedVacation)
        vacation.usedDays += usedVacation.spendDays
        vacation.freeDays -= usedVacation.spendDays
        vacationRepository.save(vacation)

        logger.info("Success insert used vacation days for $email")
    }
}
package com.vacation.tracker.dataSearch.service

import com.vacation.tracker.dataSearch.model.UsedVacation
import com.vacation.tracker.dataSearch.model.Vacation
import com.vacation.tracker.dataSearch.repository.UsedVacationRepository
import com.vacation.tracker.dataSearch.repository.VacationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class EmployeeService(
    private val vacationRepository: VacationRepository,
    private val usedVacationRepository: UsedVacationRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getVacationInfo(year: Int): Vacation {
        return vacationRepository.findByEmployeeEmailAndVacationYear(year, "user9@rbt.rs")
    }

    fun getUsedVacationDaysPerPeriod(startDate: String, endDate: String): List<UsedVacation> {

        val dataFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH)

        return usedVacationRepository.getUsedVacationPeriod(
            "user1@rbt.rs",
            dataFormat.parse(startDate),
            dataFormat.parse(endDate)
            )
    }

    fun addUsedVacationDays(usedVacation: UsedVacation) {

        val calendar = Calendar.getInstance()
        calendar.time = usedVacation.startDate
        val year = calendar.get(Calendar.YEAR)

        val vacation = vacationRepository.findByEmployeeEmailAndVacationYear(year, "user1@rbt.rs")

        usedVacation.vacation = vacation

        if (vacation.freeDays - usedVacation.spendDays < 0) {
            logger.error("Employee xxx don't have enough free days")
        } else {
            usedVacationRepository.save(usedVacation)
            vacation.usedDays += usedVacation.spendDays
            vacation.freeDays -= usedVacation.spendDays
            vacationRepository.save(vacation)
        }

        logger.info("Success insert used vacation days for xxx")
    }
}
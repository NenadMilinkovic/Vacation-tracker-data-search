package com.vacation.tracker.dataSearch.controller

import com.vacation.tracker.dataSearch.dto.UsedVacationDto
import com.vacation.tracker.dataSearch.dto.VacationInfo
import com.vacation.tracker.dataSearch.dto.VacationPeriodRequest
import com.vacation.tracker.dataSearch.dto.mapper.UsedVacationMapper
import com.vacation.tracker.dataSearch.dto.mapper.VacationMapper
import com.vacation.tracker.dataSearch.model.Employee
import com.vacation.tracker.dataSearch.service.EmployeeService
import com.vacation.tracker.dataSearch.service.TokenService
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/dataSearch")
class EmployeeController(
    private val employeeService: EmployeeService,
    private val vacationMapper: VacationMapper,
    private val usedVacationMapper: UsedVacationMapper,
    private val tokenService: TokenService
) {

    @GetMapping("/{year}/vacationInfo")
    fun getVacationInfo(@PathVariable year: Int): ResponseEntity<VacationInfo> {

        return ResponseEntity(vacationMapper.convertToDto(employeeService.getVacationInfo(year)), HttpStatus.OK)
    }

    @GetMapping("/usedVacationForPeriod")
    fun getUsedVacationPeriod(
        @RequestBody datePeriod: VacationPeriodRequest
    ): ResponseEntity<List<UsedVacationDto>> {

        return ResponseEntity(
            employeeService.getUsedVacationDaysPerPeriod(datePeriod.startDate, datePeriod.endDate).map {
                usedVacationMapper.convertToDto(it)
            },
            HttpStatus.OK
        )
    }

    @PostMapping("/addUsedVacationDays")
    fun addUsedVacationDays(
        @RequestBody usedVacationDto: UsedVacationDto
    ): ResponseEntity<*>? {
        employeeService.addUsedVacationDays(usedVacationMapper.convertToModel(usedVacationDto))
        return ResponseEntity.ok().build<Any>()
    }
}
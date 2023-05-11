package com.vacation.tracker.dataSearch.controller

import com.vacation.tracker.dataSearch.dto.UsedVacationDto
import com.vacation.tracker.dataSearch.dto.VacationInfo
import com.vacation.tracker.dataSearch.dto.mapper.UsedVacationMapper
import com.vacation.tracker.dataSearch.dto.mapper.VacationMapper
import com.vacation.tracker.dataSearch.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/dataSearch")
class EmployeeController(
    private val employeeService: EmployeeService,
    private val vacationMapper: VacationMapper,
    private val usedVacationMapper: UsedVacationMapper
) {

    @GetMapping("/{year}/vacationInfo")
    fun getVacationInfo(@PathVariable year: Int): ResponseEntity<VacationInfo> {

        return ResponseEntity(vacationMapper.convertToDto(employeeService.getVacationInfo(year)), HttpStatus.OK)
    }

    @GetMapping("/usedVacation/{startDate}/to/{endDate}")
    fun getUsedVacationPeriod(
        @PathVariable startDate: String,
        @PathVariable endDate: String
    ): ResponseEntity<List<UsedVacationDto>> {

        return ResponseEntity(
            employeeService.getUsedVacationDaysPerPeriod(startDate, endDate).map {
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
package com.vacation.tracker.dataSearch.dto.mapper

import com.vacation.tracker.dataSearch.dto.VacationInfo
import com.vacation.tracker.dataSearch.model.Vacation
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface VacationMapper {

    fun convertToDto(vacation: Vacation) : VacationInfo
    fun convertToModel(vacationInfo: VacationInfo) : Vacation
}
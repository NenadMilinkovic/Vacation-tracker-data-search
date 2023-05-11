package com.vacation.tracker.dataSearch.dto.mapper

import com.vacation.tracker.dataSearch.dto.UsedVacationDto
import com.vacation.tracker.dataSearch.model.UsedVacation
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UsedVacationMapper {

    fun convertToDto(usedVacation: UsedVacation) : UsedVacationDto
    fun convertToModel(usedVacationDto: UsedVacationDto) : UsedVacation
}
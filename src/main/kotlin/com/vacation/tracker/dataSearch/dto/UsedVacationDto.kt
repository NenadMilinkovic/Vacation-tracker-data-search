package com.vacation.tracker.dataSearch.dto

import java.util.*

data class UsedVacationDto(
    val startDate: Date,
    val endDate: Date,
    val spendDays: Int
)
package com.vacation.tracker.dataSearch.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "UsedVacation")
@Table(name = "used_vacation")
class UsedVacation(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "start_date")
    var startDate: Date = Date(),
    @Column(name = "end_date")
    var endDate: Date = Date(),
    @Column(name = "spend_days")
    var spendDays: Int = 0,
    @ManyToOne
    var vacation: Vacation = Vacation()
)
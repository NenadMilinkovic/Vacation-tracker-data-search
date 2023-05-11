package com.vacation.tracker.dataSearch.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity(name = "Vacation")
@Table(name = "vacation")
class Vacation(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "year")
    var year: Int = 2023,
    @Column(name = "days")
    var days: Int = 20,
    @Column(name = "used_days")
    var usedDays: Int = 0,
    @Column(name = "free_days")
    var freeDays: Int = 20,
    @ManyToOne
    val employee: Employee = Employee()
) {
    @OneToMany(mappedBy = "vacation", fetch = FetchType.EAGER)
    val usedVacations: List<UsedVacation> = emptyList()
}

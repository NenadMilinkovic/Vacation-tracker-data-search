package com.vacation.tracker.dataSearch.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID


@Entity(name = "Employee")
@Table(name = "employee")
class Employee (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "email", unique = true, nullable = false)
    var email: String = "",
    @Column(name = "password", nullable = false)
    var password: String = ""
) {
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    val vacations: List<Vacation> = emptyList()
}
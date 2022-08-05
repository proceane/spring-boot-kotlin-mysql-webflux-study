package com.study.webflux.domain.author

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDate
import java.time.LocalDateTime

data class Author(
    @Id val id: Int?,
    @Column("first_name") var firstName: String,
    @Column("last_name") var lastName: String,
    var email: String,
    @Column("birth_date") var birthDate: LocalDate?,
    @Column("created_at") var createdAt: LocalDateTime
) {
    companion object {
        fun createAuthor(firstName: String, lastName: String, email: String, birthDate: LocalDate?): Author = Author(
            null,
            firstName,
            lastName,
            email,
            birthDate,
            LocalDateTime.now()
        )
    }
}
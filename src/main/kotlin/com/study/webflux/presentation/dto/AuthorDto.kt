package com.study.webflux.presentation.dto

import com.study.webflux.domain.author.Author
import java.time.LocalDate
import java.time.LocalDateTime

class AuthorDto {

    class Request {
        data class Get(
            var id: Int
        ) {
        }
    }

    class Response {
        data class Get(
            val id: Int?,
            var name: String,
            var email: String,
            var birthdate: LocalDate,
            var createdAt: LocalDateTime
        ) {
            fun of(author: Author): Get = Get(
                author.id,
                author.firstName + " " + author.lastName,
                author.email,
                author.birthdate,
                author.createdAt
            )
        }
    }
}

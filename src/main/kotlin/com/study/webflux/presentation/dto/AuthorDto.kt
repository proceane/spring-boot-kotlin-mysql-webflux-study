package com.study.webflux.presentation.dto

import com.study.webflux.domain.author.Author
import java.time.LocalDate
import java.time.LocalDateTime

class AuthorDto {

    class Request {
        data class Post(
            var firstName: String,
            var lastName: String,
            var email: String,
            var birthDate: LocalDate?
        )

        data class Patch(
            var firstName: String,
            var lastName: String,
            var email: String,
            var birthdate: LocalDate?
        )
    }

    class Response {
        data class Get(
            val id: Int?,
            var name: String,
            var email: String,
            var birthdate: LocalDate?,
            var createdAt: LocalDateTime
        ) {
            companion object {
                fun of(author: Author): Get = Get(
                    author.id,
                    author.firstName + " " + author.lastName,
                    author.email,
                    author.birthDate,
                    author.createdAt
                )
            }
        }
    }
}

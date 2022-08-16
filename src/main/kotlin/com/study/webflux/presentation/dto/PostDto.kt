package com.study.webflux.presentation.dto

import java.time.LocalDateTime

class PostDto {

    class Request {
        data class Post(
            var title: String,
            var description: String,
            var content: String,
        )

        data class Patch(
            var title: String?,
            var description: String?,
            var content: String?,
        )
    }

    class Response {
        data class Get(
            val id: Int?,
            var authorId: Int,
            var title: String,
            var description: String,
            var content: String,
            var createdAt: LocalDateTime
        )
    }
}
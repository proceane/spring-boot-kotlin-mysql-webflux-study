package com.study.webflux.presentation.dto

import com.study.webflux.domain.post.Post
import java.time.LocalDateTime

class PostDto {

    class Request {
        data class Post(
            var authorId: Int,
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
            var authorName: String,
            var title: String,
            var description: String,
            var content: String,
            var createdAt: LocalDateTime
        ) {
            companion object {
                fun of(post: Post, authorName: String): Get = Get(
                    post.id,
                    authorName,
                    post.title,
                    post.description,
                    post.content,
                    post.createdAt
                )
            }
        }
    }
}
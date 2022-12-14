package com.study.webflux.domain.post

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

data class Post(
    @Id val id: Int?,
    @Column("author_id") var authorId: Int,
    var title: String,
    var description: String,
    var content: String,
    @Column("created_at") var createdAt: LocalDateTime
) {
    companion object {
        fun createPost(authorId: Int, title: String, description: String, content: String): Post = Post(
            null,
            authorId,
            title,
            description,
            content,
            LocalDateTime.now()
        )
    }

    fun updatePost(title: String?, description: String?, content: String?) {
        title?.let { this.title = title }
        description?.let { this.description = description }
        content?.let { this.content = content }
    }
}
package com.study.webflux.infra.repository

import com.study.webflux.domain.post.Post
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : R2dbcRepository<Post, Int> {
}
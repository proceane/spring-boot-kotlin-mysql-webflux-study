package com.study.webflux.infra.repository

import com.study.webflux.domain.author.Author
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : R2dbcRepository<Author, Int>, AuthorCustomRepository {
}
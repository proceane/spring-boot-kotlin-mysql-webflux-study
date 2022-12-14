package com.study.webflux.infra.repository

import com.study.webflux.domain.author.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Mono

interface AuthorCustomRepository {
    fun findAll(pageable: Pageable): Mono<Page<Author>>
}
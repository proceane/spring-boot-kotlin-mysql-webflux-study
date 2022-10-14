package com.study.webflux.infra.repository

import com.study.webflux.domain.author.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class AuthorCustomRepositoryImpl: AuthorCustomRepository {
    override fun findAll(pageable: Pageable): Mono<Page<Author>> {
        return Mono.empty()
    }
}
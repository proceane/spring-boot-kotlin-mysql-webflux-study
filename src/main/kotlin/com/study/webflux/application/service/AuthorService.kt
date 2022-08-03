package com.study.webflux.application.service

import com.study.webflux.infra.repository.AuthorRepository
import com.study.webflux.presentation.dto.AuthorDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthorService(val authorRepository: AuthorRepository) {

    fun get(id: Int): Mono<AuthorDto.Response.Get> = authorRepository.findById(id)
        .map { AuthorDto.Response.Get.of(it) }

}
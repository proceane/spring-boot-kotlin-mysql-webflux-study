package com.study.webflux.application.service

import com.study.webflux.domain.author.Author
import com.study.webflux.infra.repository.AuthorRepository
import com.study.webflux.presentation.dto.AuthorDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AuthorService(val authorRepository: AuthorRepository) {

    fun get(id: Int): Mono<AuthorDto.Response.Get> = authorRepository.findById(id)
        .map { AuthorDto.Response.Get.of(it) }

    fun getAll(): Flux<AuthorDto.Response.Get> = authorRepository.findAll()
        .map { AuthorDto.Response.Get.of(it) }

    fun post(dto: AuthorDto.Request.Post): Mono<AuthorDto.Response.Get> {
        return authorRepository.save(Author.createAuthor(dto.firstName, dto.lastName, dto.email, dto.birthDate))
            .map { AuthorDto.Response.Get.of(it) }
    }

    fun patch(id: Int, dto: AuthorDto.Request.Patch): Mono<AuthorDto.Response.Get> {
        return authorRepository.findById(id).flatMap {
            it.updateAuthor(dto.firstName, dto.lastName, dto.email, dto.birthDate)
            authorRepository.save(it)
        }.map { AuthorDto.Response.Get.of(it) }
    }

    fun delete(id: Int) {
        authorRepository.deleteById(id).subscribe()
    }

}


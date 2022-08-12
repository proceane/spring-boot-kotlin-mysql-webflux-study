package com.study.webflux.presentation.controller

import com.study.webflux.application.service.AuthorService
import com.study.webflux.presentation.dto.AuthorDto
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class AuthorControllerV1(val authorService: AuthorService) {

    @GetMapping("/author/v1/{id}")
    fun get(@PathVariable id: Int): Mono<AuthorDto.Response.Get> = authorService.get(id)

    @GetMapping("/author/v1")
    fun getAll(): Flux<AuthorDto.Response.Get> = authorService.getAll()

    @PostMapping("/author/v1")
    fun post(@RequestBody dto: AuthorDto.Request.Post): Mono<AuthorDto.Response.Get> = authorService.post(dto)

    @PatchMapping("/author/v1/{id}")
    fun patch(@PathVariable id: Int, @RequestBody dto: AuthorDto.Request.Patch): Mono<AuthorDto.Response.Get> =
        authorService.patch(id, dto)

    @DeleteMapping("/author/v1/{id}")
    fun delete(@PathVariable id: Int) = authorService.delete(id)

}
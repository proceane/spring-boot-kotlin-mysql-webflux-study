package com.study.webflux.presentation.controller

import com.study.webflux.application.service.AuthorService
import com.study.webflux.presentation.dto.AuthorDto
import com.study.webflux.presentation.uri.Uri
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class AuthorControllerV1(val authorService: AuthorService) {

    @GetMapping(Uri.Author.V1_SINGLE)
    fun get(@PathVariable id: Int): Mono<AuthorDto.Response.Get> = authorService.get(id)

    @GetMapping(Uri.Author.V1_COLLECTION)
    fun getAll(): Flux<AuthorDto.Response.Get> = authorService.getAll()

    @PostMapping(Uri.Author.V1_COLLECTION)
    fun post(@RequestBody dto: AuthorDto.Request.Post): Mono<AuthorDto.Response.Get> = authorService.post(dto)

    @PatchMapping(Uri.Author.V1_SINGLE)
    fun patch(@PathVariable id: Int, @RequestBody dto: AuthorDto.Request.Patch): Mono<AuthorDto.Response.Get> =
        authorService.patch(id, dto)

    @DeleteMapping(Uri.Author.V1_SINGLE)
    fun delete(@PathVariable id: Int) = authorService.delete(id)

}
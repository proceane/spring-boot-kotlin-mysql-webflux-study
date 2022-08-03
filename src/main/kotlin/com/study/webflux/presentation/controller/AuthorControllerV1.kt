package com.study.webflux.presentation.controller

import com.study.webflux.application.service.AuthorService
import com.study.webflux.presentation.dto.AuthorDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthorControllerV1(val authorService: AuthorService) {

    @GetMapping("/author/v1/{id}")
    fun get(@PathVariable id: Int) : Mono<AuthorDto.Response.Get> = authorService.get(id)
}
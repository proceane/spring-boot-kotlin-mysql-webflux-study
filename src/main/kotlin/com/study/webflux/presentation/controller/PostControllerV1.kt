package com.study.webflux.presentation.controller

import com.study.webflux.application.service.PostService
import com.study.webflux.presentation.dto.PostDto
import com.study.webflux.presentation.uri.Uri
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class PostControllerV1(val postService: PostService) {

    @GetMapping(Uri.Post.V1.SINGLE)
    fun get(@PathVariable id: Int): Mono<PostDto.Response.Get> = postService.get(id)

    @GetMapping(Uri.Post.V1.COLLECTION)
    fun getAll(): Flux<PostDto.Response.Get> = postService.getAll()

    @PatchMapping(Uri.Post.V1.SINGLE)
    fun patch(@PathVariable id: Int, @RequestBody dto: PostDto.Request.Patch): Mono<PostDto.Response.Get> =
        postService.patch(id, dto)

    @DeleteMapping(Uri.Post.V1.SINGLE)
    fun delete(@PathVariable id: Int) = postService.delete(id)
}
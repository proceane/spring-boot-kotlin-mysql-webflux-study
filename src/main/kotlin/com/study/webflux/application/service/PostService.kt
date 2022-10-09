package com.study.webflux.application.service

import com.study.webflux.domain.post.Post
import com.study.webflux.infra.repository.AuthorRepository
import com.study.webflux.infra.repository.PostRepository
import com.study.webflux.presentation.dto.PostDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PostService(val postRepository: PostRepository, val authorRepository: AuthorRepository) {

    fun get(id: Int): Mono<PostDto.Response.Get> = getResponse(postRepository.findById(id))

    fun getAll(): Flux<PostDto.Response.Get> = postRepository.findAll()
        .map { PostDto.Response.Get.of(it, "") }

    fun post(dto: PostDto.Request.Post): Mono<PostDto.Response.Get> {
        return getResponse(postRepository.save(Post.createPost(dto.authorId, dto.title, dto.description, dto.content)))
    }

    fun patch(id: Int, dto: PostDto.Request.Patch): Mono<PostDto.Response.Get> {
        return getResponse(postRepository.findById(id).flatMap {
            it.updatePost(dto.title, dto.description, dto.content)
            postRepository.save(it)})
    }
    
    fun delete(id: Int) {
        postRepository.deleteById(id).subscribe()
    }

    fun getResponse(mono: Mono<Post>): Mono<PostDto.Response.Get> {
        return mono.flatMap { post -> authorRepository.findById(post.authorId)
            .map { author -> PostDto.Response.Get.of(post, author.getName()) }}
    }
}
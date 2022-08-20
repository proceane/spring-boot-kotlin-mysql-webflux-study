package com.study.webflux.application.service

import com.study.webflux.domain.post.Post
import com.study.webflux.infra.repository.PostRepository
import com.study.webflux.presentation.dto.PostDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PostService(val postRepository: PostRepository) {

    fun get(id: Int): Mono<PostDto.Response.Get> = postRepository.findById(id)
        .map { PostDto.Response.Get.of(it) }

    fun getAll(): Flux<PostDto.Response.Get> = postRepository.findAll()
        .map { PostDto.Response.Get.of(it) }

    fun post(authorId: Int, dto: PostDto.Request.Post): Mono<PostDto.Response.Get> {
        return postRepository.save(Post.createPost(authorId, dto.title, dto.description, dto.content))
            .map { PostDto.Response.Get.of(it) }
    }

    fun patch(id: Int, dto: PostDto.Request.Patch): Mono<PostDto.Response.Get> {
        return postRepository.findById(id).flatMap {
            it.updatePost(dto.title, dto.description, dto.content)
            postRepository.save(it)
        }.map { PostDto.Response.Get.of(it) }
    }
    
    fun delete(id: Int) {
        postRepository.deleteById(id).subscribe()
    }
}
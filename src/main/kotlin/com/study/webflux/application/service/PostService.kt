package com.study.webflux.application.service

import com.study.webflux.infra.repository.PostRepository
import com.study.webflux.presentation.dto.PostDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PostService(val postRepository: PostRepository) {

    fun get(id: Int): Mono<PostDto.Response.Get> = postRepository.findById(id)
        .map { PostDto.Response.Get.of(it) }
}
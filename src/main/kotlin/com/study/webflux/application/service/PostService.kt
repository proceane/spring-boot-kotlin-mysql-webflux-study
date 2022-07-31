package com.study.webflux.application.service

import com.study.webflux.infra.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(val postRepository: PostRepository) {
}
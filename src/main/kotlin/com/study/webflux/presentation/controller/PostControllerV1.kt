package com.study.webflux.presentation.controller

import com.study.webflux.application.service.PostService
import org.springframework.web.bind.annotation.RestController

@RestController
class PostControllerV1(val postService: PostService) {
}
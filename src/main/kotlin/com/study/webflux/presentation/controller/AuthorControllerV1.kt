package com.study.webflux.presentation.controller

import com.study.webflux.application.service.AuthorService
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorControllerV1(val authorService: AuthorService) {
}
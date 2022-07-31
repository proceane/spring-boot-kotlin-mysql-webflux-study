package com.study.webflux.application.service

import com.study.webflux.infra.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(val authorRepository: AuthorRepository) {
}
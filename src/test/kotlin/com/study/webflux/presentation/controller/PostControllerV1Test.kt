package com.study.webflux.presentation.controller

import com.study.webflux.infra.repository.PostRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(RestDocumentationExtension::class)
class PostControllerV1Test() {

    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var postRepository: PostRepository

    @BeforeEach
    fun setUp(context: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        this.webTestClient = MockMvcWebTestClient.bindToApplicationContext(context)
            .configureClient()
            .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }

    @Test
    fun getAll() {
        webTestClient.get().uri("/v1/posts").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(
                WebTestClientRestDocumentation.document(
                    "post-get-all",
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("[].id").description("Post's idx"),
                        PayloadDocumentation.fieldWithPath("[].authorId").description("Post's authorId"),
                        PayloadDocumentation.fieldWithPath("[].title").description("Post's title"),
                        PayloadDocumentation.fieldWithPath("[].description").description("Post's description"),
                        PayloadDocumentation.fieldWithPath("[].content").description("Post's content"),
                        PayloadDocumentation.fieldWithPath("[].createdAt")
                            .description("The time the post data created")
                    )
                )
            )
    }

    @Test
    fun getOne() {

    }

    @Test
    fun post() {

    }

    @Test
    fun patch() {

    }

    @Test
    fun delete() {

    }

}
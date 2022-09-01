package com.study.webflux.presentation.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(RestDocumentationExtension::class)
class AuthorControllerV1Test() {

    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp(context: WebApplicationContext, restDocumentation: RestDocumentationContextProvider) {
        this.webTestClient = MockMvcWebTestClient.bindToApplicationContext(context)
            .configureClient()
            .filter(documentationConfiguration(restDocumentation))
            .build()
    }

    @Test
    fun getAll() {
        webTestClient.get().uri("/v1/authors").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(
                document(
                    "author-get-all",
                    responseFields(
                        fieldWithPath("[].id").description("Author's idx"),
                        fieldWithPath("[].name").description("Author's name"),
                        fieldWithPath("[].email").description("Author's email"),
                        fieldWithPath("[].birthdate").description("Author's birthday"),
                        fieldWithPath("[].createdAt").description("The time the author data created")
                    )
                )
            )
    }

    @Test
    fun getOne() {
        webTestClient.get().uri("/v1/authors/1").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(
                document(
                    "author-get",
                    responseFields(
                        fieldWithPath("id").description("Author's idx"),
                        fieldWithPath("name").description("Author's name"),
                        fieldWithPath("email").description("Author's email"),
                        fieldWithPath("birthdate").description("Author's birthday"),
                        fieldWithPath("createdAt").description("The time the author data created")
                    )
                )
            )
    }


}
package com.study.webflux.presentation.controller

import com.study.webflux.infra.repository.AuthorRepository
import com.study.webflux.presentation.dto.AuthorDto
import org.json.JSONObject
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
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.reactive.function.BodyInserters
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(RestDocumentationExtension::class)
class AuthorControllerV1Test() {

    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var authorRepository: AuthorRepository

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
        webTestClient.get().uri("/v1/authors/5").accept(MediaType.APPLICATION_JSON)
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

    @Test
    fun post() {
        var requestBody: AuthorDto.Request.Post = AuthorDto.Request.Post("first", "last", "mail2@mail.com", LocalDate.now())

        webTestClient.post().uri("/v1/authors").accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestBody))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo(requestBody.firstName + " " + requestBody.lastName)
            .jsonPath("$.email").isEqualTo(requestBody.email)
            .consumeWith(
                document(
                    "author-post",
                    responseFields(
                        fieldWithPath("id").description("Author's idx"),
                        fieldWithPath("name").description("Author's name"),
                        fieldWithPath("email").description("Author's email"),
                        fieldWithPath("birthdate").description("Author's birthday"),
                        fieldWithPath("createdAt").description("The time the author data created")
                    )
                ))
            .consumeWith { result ->
                result.responseBody?.let {
                    val jsonObject = JSONObject(String(it))
                    authorRepository.deleteById(jsonObject.getInt("id")).subscribe()
                }
            }
    }

    @Test
    fun patch() {
        // given
        var requestBody: AuthorDto.Request.Patch = AuthorDto.Request.Patch("first", "last", "mail@mail.com", LocalDate.now())

        webTestClient.patch().uri("/v1/authors/2").accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestBody))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(
                document(
                    "author-patch",
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

    @Test
    fun delete() {
        webTestClient.delete().uri("/v1/authors/1").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .consumeWith(
                document(
                    "author-delete",
                )
            )
    }

}
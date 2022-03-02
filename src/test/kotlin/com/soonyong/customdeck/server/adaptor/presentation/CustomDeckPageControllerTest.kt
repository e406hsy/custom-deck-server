package com.soonyong.customdeck.server.adaptor.presentation

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

private val log = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomDeckPageControllerTest(webTestClient: WebTestClient) : FunSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {

        test("testGetPages") {
            val result = webTestClient.get().uri("/api/pages")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.data").exists()
                .returnResult()

            log.debug { "$result" }

        }
    }


}

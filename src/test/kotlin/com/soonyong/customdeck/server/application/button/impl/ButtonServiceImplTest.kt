package com.soonyong.customdeck.server.application.button.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

private val log = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ButtonServiceImplTest : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

}

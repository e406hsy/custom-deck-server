package com.soonyong.customdeck.server.application.button.impl

import com.ninjasquad.springmockk.MockkBean
import com.soonyong.customdeck.server.application.keyboard.KeyboardEventListener
import com.soonyong.customdeck.server.domain.button.ButtonRepository
import com.soonyong.customdeck.server.domain.button.model.Button
import com.soonyong.customdeck.server.domain.button.model.ButtonType
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

private val log = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ButtonServiceImplTest(buttonServiceImpl: ButtonServiceImpl) : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    lateinit var keyboardEventListener: KeyboardEventListener

    @MockkBean
    lateinit var buttonRepository: ButtonRepository

    init {
        "testKeyboardEvent" {

            every { buttonRepository.getButton(1) } answers { Button(id = 1, type = ButtonType.KeyBoard) }
            every { keyboardEventListener.handleKeyboardPressEvent(any()) } just runs
            buttonServiceImpl.onButtonPress(1)

            verify(exactly = 1) { keyboardEventListener.handleKeyboardPressEvent(allAny()) }
        }
    }
}

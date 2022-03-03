package com.soonyong.customdeck.server.application.button.impl

import com.soonyong.customdeck.server.application.button.ButtonService
import com.soonyong.customdeck.server.application.keyboard.model.KeyboardPressEvent
import com.soonyong.customdeck.server.domain.button.ButtonRepository
import com.soonyong.customdeck.server.domain.button.model.Button
import com.soonyong.customdeck.server.domain.button.model.ButtonType
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class ButtonServiceImpl(private val publisher: ApplicationEventPublisher, private val buttonRepository: ButtonRepository) :ButtonService {
    override fun getButtons(): List<Button> = TODO()

    override fun getButton(id: Int): Button =        buttonRepository.getButton(id)


    override fun onButtonPress(buttonId: Int) {
        log.debug { "button pressed $buttonId" }

        val button = getButton(buttonId)

        when (button.type) {
            ButtonType.KeyBoard -> publisher.publishEvent(KeyboardPressEvent(button.value))
            ButtonType.None -> {}
        }

    }
}
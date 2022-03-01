package com.soonyong.customdeck.server.application.button.impl

import com.soonyong.customdeck.server.application.button.ButtonService
import com.soonyong.customdeck.server.application.keyboard.model.KeyboardPressEvent
import com.soonyong.customdeck.server.domain.button.ButtonRepository
import com.soonyong.customdeck.server.domain.button.model.Button
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ButtonServiceImpl(private val publisher: ApplicationEventPublisher, private val buttonRepository: ButtonRepository) :ButtonService {
    override fun getButtons(): List<Button> = TODO()
    override fun pressButtons(buttonId: Long) {
        publisher.publishEvent(KeyboardPressEvent())
    }

    override fun getButton(id: Long): Mono<Button> {
        TODO()
    }
}
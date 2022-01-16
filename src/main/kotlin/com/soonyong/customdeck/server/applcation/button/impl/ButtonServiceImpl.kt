package com.soonyong.customdeck.server.applcation.button.impl

import com.soonyong.customdeck.server.applcation.button.ButtonService
import com.soonyong.customdeck.server.applcation.keyboard.model.KeyboardPressEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ButtonServiceImpl(val publisher: ApplicationEventPublisher) :ButtonService {
    override fun getButtons(): Flux<Long> = Flux.just(2)
    override fun pressButtons(buttonId: Long) {
        publisher.publishEvent(KeyboardPressEvent())
    }

    override fun getButton(): Long {
        TODO("Not yet implemented")
    }
}
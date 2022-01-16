package com.soonyong.customdeck.server.applcation.keyboard

import com.soonyong.customdeck.server.applcation.keyboard.model.KeyboardPressEvent
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}
@Component
class KeyboardEventListener {

    @EventListener
    fun handleKeyboardPressEvent(keyboardPressEvent: KeyboardPressEvent) {
        log.debug { "Handle $keyboardPressEvent" }
    }
}
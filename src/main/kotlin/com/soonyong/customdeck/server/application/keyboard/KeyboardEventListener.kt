package com.soonyong.customdeck.server.application.keyboard

import com.soonyong.customdeck.server.application.keyboard.model.KeyboardPressEvent
import mu.KotlinLogging
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
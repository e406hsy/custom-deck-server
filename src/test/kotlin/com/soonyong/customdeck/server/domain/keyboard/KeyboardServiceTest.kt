package com.soonyong.customdeck.server.domain.keyboard

import io.kotest.core.spec.style.FunSpec
import java.awt.event.KeyEvent

class KeyboardServiceTest : FunSpec({
    test("test") {
        KeyboardService().press(KeyEvent.VK_RIGHT)
    }

    test("testPressMultipleKeys") {
        val keyboardService = KeyboardService()

        keyboardService.press(durationInMillis = 200, KeyEvent.VK_CONTROL, KeyEvent.VK_RIGHT)
    }

})

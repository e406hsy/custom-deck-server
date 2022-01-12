package com.soonyong.customdeck.server.domain.keyboard

import io.kotest.core.spec.style.FunSpec
import java.awt.event.KeyEvent

class KeyboardServiceTest : FunSpec({
    test("test") {
        KeyboardService().press(KeyEvent.VK_NUMPAD4)
    }

})

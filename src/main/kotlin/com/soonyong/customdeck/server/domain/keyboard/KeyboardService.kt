package com.soonyong.customdeck.server.domain.keyboard

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.awt.Robot

private val log = KotlinLogging.logger {}

@Service
class KeyboardService {

    private val robot: Robot = Robot()

    fun press(durationInMillis: Int = 100, vararg keyCodes: Int) {
        keyCodes.forEach {
            log.debug { "pressing key $it" }
            robot.keyPress(it)
        }
        robot.delay(durationInMillis)
        keyCodes.reversed().forEach {
            log.debug { "releasing key $it" }
            robot.keyRelease(it)
        }
    }

    fun press(keyCode: Int, durationInMillis: Int = 100) {
        log.debug { "press called with keyCode:$keyCode, duration:$durationInMillis" }
        robot.keyPress(keyCode)
        robot.delay(durationInMillis)
        robot.keyRelease(keyCode)
        log.debug { "press end" }
    }
}
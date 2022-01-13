package com.soonyong.customdeck.server.domain.keyboard

import org.springframework.stereotype.Service
import java.awt.Robot

@Service
class KeyboardService {

    private val robot: Robot = Robot()

    fun press(keyCode: Int, durationInMillis: Int = 100) {
        robot.keyPress(keyCode)
        robot.delay(durationInMillis)
        robot.keyRelease(keyCode)
    }
}
package com.soonyong.customdeck.server.ui

import javafx.scene.paint.Color
import tornadofx.*

class MasterStyle : Stylesheet() {

    companion object {
        val customDeckButton by cssclass()

        private val topColor = Color.RED
        private val rightColor = Color.DARKGREEN
        private val leftColor = Color.ORANGE
        private val bottomColor = Color.PURPLE
    }

    init {
        customDeckButton {
            cellSize = 30.px
            borderColor += box(topColor, rightColor, bottomColor, leftColor)
            fontFamily = "Comic Sans MS"
            fontSize = 20.px
            startMargin = 20.px
            endMargin = 20.px
        }
    }
}
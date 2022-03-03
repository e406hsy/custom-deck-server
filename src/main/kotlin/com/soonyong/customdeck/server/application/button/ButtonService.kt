package com.soonyong.customdeck.server.application.button

import com.soonyong.customdeck.server.domain.button.model.Button

interface ButtonService {
    fun getButtons(): List<Button>

    fun getButton(id: Int): Button

    fun onButtonPress(buttonId: Int)
}
package com.soonyong.customdeck.server.application.button

import com.soonyong.customdeck.server.domain.button.model.Button
import reactor.core.publisher.Mono

interface ButtonService {
    fun getButtons(): List<Button>

    fun pressButtons(buttonId: Long)

    fun getButton(id: Long): Mono<Button>
}
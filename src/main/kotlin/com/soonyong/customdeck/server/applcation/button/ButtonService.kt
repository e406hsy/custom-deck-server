package com.soonyong.customdeck.server.applcation.button

import com.soonyong.customdeck.server.domain.button.model.Button
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ButtonService {
    fun getButtons(): Flux<Button>

    fun pressButtons(buttonId: Long)

    fun getButton(id: Long): Mono<Button>
}
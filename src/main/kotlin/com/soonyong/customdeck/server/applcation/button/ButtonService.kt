package com.soonyong.customdeck.server.applcation.button

import reactor.core.publisher.Flux

interface ButtonService {
    fun getButtons(): Flux<Long>

    fun pressButtons(buttonId: Long)

    fun getButton(): Long
}
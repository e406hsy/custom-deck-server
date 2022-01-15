package com.soonyong.customdeck.server.applcation.button

import reactor.core.publisher.Flux

interface ButtonService {
    fun getButtons(): Flux<String>
}
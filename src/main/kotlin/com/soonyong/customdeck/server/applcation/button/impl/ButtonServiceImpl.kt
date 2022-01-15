package com.soonyong.customdeck.server.applcation.button.impl

import com.soonyong.customdeck.server.applcation.button.ButtonService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ButtonServiceImpl :ButtonService {
    override fun getButtons(): Flux<String> = Flux.just("dummy")
}
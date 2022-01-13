package com.soonyong.customdeck.server.adaptor

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class WebSocketHandlerImpl : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        TODO("Not yet implemented")
    }
}
package com.soonyong.customdeck.server.adaptor

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger {}

@Component
class WebSocketHandlerImpl : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        val output = session.receive()
            .log()
            .doOnNext {
                log.info { "message received. $it. ${it.payloadAsText}" }
            }.map {
                log.info { "message mapping. $it" }
                session.textMessage("Successful")
            }
        return session.send(output)
    }
}
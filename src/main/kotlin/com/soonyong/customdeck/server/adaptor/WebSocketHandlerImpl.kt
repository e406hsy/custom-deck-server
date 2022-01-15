package com.soonyong.customdeck.server.adaptor

import com.soonyong.customdeck.server.applcation.button.ButtonService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger {}

@Component
class WebSocketHandlerImpl(private val buttonService: ButtonService) : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        log.info { "handle called with $session, ${session.handshakeInfo}, ${session.handshakeInfo.remoteAddress}" }
        val input = session.receive().doFirst {
            log.info { "socket connected" }
        }.doAfterTerminate {
            log.info { "socket closed" }
        }.log().doOnNext {
            log.info { "message received. $it. ${it.payloadAsText}" }
        }.then()

        val output = session.send(buttonService.getButtons().map(session::textMessage))
        return Mono.zip(input, output).then()
    }
}
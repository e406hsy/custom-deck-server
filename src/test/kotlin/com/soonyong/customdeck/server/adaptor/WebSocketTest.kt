package com.soonyong.customdeck.server.adaptor

import mu.KotlinLogging
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.net.URI
import java.time.Duration

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    val client: WebSocketClient = ReactorNettyWebSocketClient()
    client.execute(
        URI.create("ws://localhost:8080/endpoint")
    ) { session ->
        session.send(Flux.range(0, 100).map { "message$it" }.map { session.textMessage(it) }.doOnNext {
            log.info { "message send. ${it.payloadAsText}" }
        }).log().and {
            session.receive().log().map(WebSocketMessage::getPayloadAsText).doOnNext {
                log.info { "message received. $it" }
            }.log().subscribeOn(Schedulers.parallel()).subscribe()
        }.subscribeOn(Schedulers.parallel()).then()
    }.block(Duration.ofSeconds(10L))
}


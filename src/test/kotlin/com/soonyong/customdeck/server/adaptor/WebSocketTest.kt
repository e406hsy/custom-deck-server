package com.soonyong.customdeck.server.adaptor

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.shouldBeExactly
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.net.URI
import java.time.Duration

private val log = KotlinLogging.logger {}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WebSocketTest : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    @LocalServerPort
    lateinit var port: Integer

    init {
        System.setProperty("java.awt.headless", "false")

        "testWebSocket" {
            val client: WebSocketClient = ReactorNettyWebSocketClient()

            var sendCount = 0
            var receiveCount = 0
            try {
                client.execute(
                    URI.create("ws://localhost:${port}/endpoint")
                ) { session ->
                    session.send(Flux.range(0, 10).map { "message$it" }.map { session.textMessage(it) }.doOnNext {
                        log.info { "message send. ${it.payloadAsText}" }
                        sendCount++
                    }).log().and {
                        session.receive().log().map(WebSocketMessage::getPayloadAsText).doOnNext {
                            log.info { "message received. $it" }
                            receiveCount++
                        }.log().subscribeOn(Schedulers.parallel()).subscribe()
                    }.subscribeOn(Schedulers.parallel()).then()
                }.block(Duration.ofSeconds(10L))
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }
            sendCount shouldBeExactly 10
            receiveCount shouldBeExactly 10

        }
    }

}


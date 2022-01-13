package com.soonyong.customdeck.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler

private const val RIGHT_BEFORE_CONTROLLER = -1

@Configuration
class EndPointConfig {
    @Bean
    fun handlerMapping(webSocketHandler: WebSocketHandler): HandlerMapping {
        val mapping = mapOf("/endpoint" to webSocketHandler)
        return SimpleUrlHandlerMapping(mapping, RIGHT_BEFORE_CONTROLLER)
    }
}
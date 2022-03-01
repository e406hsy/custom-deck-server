package com.soonyong.customdeck.server.adaptor.filter

import com.soonyong.customdeck.server.application.acl.IpAclService
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class IpAclFilter(val ipAclService: IpAclService) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        if (ipAclService.isAllow(exchange.request.remoteAddress)) {
            return chain.filter(exchange)
        }
        // TODO : 302 response
        return Mono.empty()
    }
}
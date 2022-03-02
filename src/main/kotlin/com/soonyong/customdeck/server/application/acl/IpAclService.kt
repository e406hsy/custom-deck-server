package com.soonyong.customdeck.server.application.acl

import org.springframework.stereotype.Service
import java.net.InetSocketAddress

@Service
class IpAclService {
    fun isAllow(remoteAddress: InetSocketAddress?): Boolean {
        //TODO : implement
        return true
    }
}
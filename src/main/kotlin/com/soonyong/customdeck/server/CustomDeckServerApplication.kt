package com.soonyong.customdeck.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomDeckServerApplication

fun main(args: Array<String>) {
    System.setProperty("java.awt.headless","false")
    runApplication<CustomDeckServerApplication>(*args)
}

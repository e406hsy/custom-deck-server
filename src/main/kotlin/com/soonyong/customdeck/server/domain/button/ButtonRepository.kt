package com.soonyong.customdeck.server.domain.button

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.soonyong.customdeck.server.domain.button.model.Button
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.io.File

@Repository
class ButtonRepository(fileName: String) {

    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val file = File(fileName)
    init {
        if (!file.exists()) {
            file.createNewFile()
            file.writer().use {
                it.write("{}")
            }
        }
    }
    private val buttons: MutableMap<Long, Button> = objectMapper.readValue(file, objectMapper.typeFactory.constructParametricType(MutableMap::class.java, Long::class.java, Button::class.java))


    fun getButtons(): Mono<Collection<Button>> {
        return Mono.just(buttons.entries.sortedBy { it.key }.map { it.value })
    }

    fun getButton(id : Long) : Mono<Button> {
        return Mono.just(buttons[id]?.copy() ?: Button(id))
    }

    fun setButton(button: Button){
        buttons[button.id] = button
    }

    private fun saveButton(button: Button){
        file.outputStream().use { it.write(objectMapper.writeValueAsBytes(buttons)) }
    }
}
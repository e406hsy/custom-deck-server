package com.soonyong.customdeck.server.domain.button

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.soonyong.customdeck.server.domain.button.model.Button
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class ButtonRepository(@Value("\${file.page}") pageFileName: String, @Value("\${button.file}") buttonFileName: String) {

    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val buttonFile = File(buttonFileName)
    private val pageFile = File(pageFileName)
    private val pages : MutableMap<Int, String>
    private val buttons: MutableMap<Int, MutableMap<Int, Button>>

    init {
        if (!buttonFile.exists()) {
            buttonFile.createNewFile()
            buttonFile.writer().use {
                it.write("{}")
            }
        }
        if (!pageFile.exists()) {
            pageFile.createNewFile()
            pageFile.writer().use {
                it.write("{}")
            }
        }

        val inner = objectMapper.typeFactory.constructParametricType(
            MutableMap::class.java,
            Int::class.java,
            Button::class.java
        )

        pages = mutableMapOf()

        buttons =
            objectMapper.readValue(
                buttonFile,
                objectMapper.typeFactory.constructParametricType(
                    MutableMap::class.java,
                    Int::class.java,
                    Button::class.java
                )
            )
    }

//
//    suspend fun getCustomDeckPage(pageId: Int): CustomDeckPage {
//
//        return
//    }
//
//    fun getButtons(): Mono<Collection<Button>> {
//        return Mono.just(buttons.entries.sortedBy { it.key }.map { it.value })
//    }
//
//    fun getButton(id: Long): Mono<Button> {
//        return Mono.just(buttons[id]?.copy() ?: Button(id))
//    }
//
//    fun setButton(button: Button) {
//        buttons[button.id] = button.copy()
//        saveButton()
//    }
//
//    private fun saveButton() {
//        buttonFile.outputStream().use { it.write(objectMapper.writeValueAsBytes(buttons)) }
//    }
}
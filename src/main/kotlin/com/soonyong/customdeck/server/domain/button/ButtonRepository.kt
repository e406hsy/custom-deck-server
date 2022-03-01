package com.soonyong.customdeck.server.domain.button

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.soonyong.customdeck.server.domain.button.model.Button
import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import com.soonyong.customdeck.server.domain.button.vo.SimpleCustomDeckPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class ButtonRepository(@Value("\${file.button}") buttonFileName: String) {

    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val buttonFile = File(buttonFileName)
    private val buttons: MutableMap<Int, Button>

    init {
        if (!buttonFile.exists()) {
            buttonFile.createNewFile()
            buttonFile.writer().use {
                it.write("{}")
            }
        }
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



    fun getButton(id: Int): Button {
        return buttons[id]?.copy() ?: Button(id)
    }

    suspend fun setButton(button: Button) {
        buttons[button.id] = button.copy()
        withContext(Dispatchers.IO) {
            saveButton()
        }
    }

    private suspend fun saveButton() {
        buttonFile.outputStream().use { it.write(objectMapper.writeValueAsBytes(buttons)) }
    }
}
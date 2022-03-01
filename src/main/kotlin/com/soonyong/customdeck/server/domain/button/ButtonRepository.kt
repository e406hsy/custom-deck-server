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
class ButtonRepository(@Value("\${file.page}") pageFileName: String, @Value("\${button.file}") buttonFileName: String) {

    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val buttonFile = File(buttonFileName)
    private val pageFile = File(pageFileName)
    private val pages: MutableMap<Int, SimpleCustomDeckPage>
    private val buttons: MutableMap<Int, Button>

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

        pages =
            objectMapper.readValue(
                pageFile,
                objectMapper.typeFactory.constructParametricType(
                    MutableMap::class.java,
                    Int::class.java,
                    SimpleCustomDeckPage::class.java
                )
            )

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


    fun getCustomDeckPage(pageId: Int): CustomDeckPage {
        return with(pages[pageId] ?: throw IllegalArgumentException("page${pageId} not found")) {
            CustomDeckPage(
                xCount = this.xCount,
                yCount = this.yCount,
                buttons = this.buttonIds.map { buttons[it] ?: Button(0) }.toMutableList()
            )
        }
    }

    fun setCustomDeckPage(customDeckPage: CustomDeckPage) {

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
package com.soonyong.customdeck.server.domain.button

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import com.soonyong.customdeck.server.domain.button.vo.SimpleCustomDeckPage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class PageRepository(@Value("\${file.page}") pageFileName: String, _buttonRepository: ButtonRepository) {
    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val pageFile = File(pageFileName)
    private val pages: MutableMap<Int, SimpleCustomDeckPage>
    private val buttonRepository = _buttonRepository

    init {

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

    }


    fun getCustomDeckPage(pageId: Int): CustomDeckPage {
        return with(pages[pageId] ?: throw IllegalArgumentException("page${pageId} not found")) {
            CustomDeckPage(
                xCount = this.xCount,
                yCount = this.yCount,
                buttons = this.buttonIds.map { buttonRepository.getButton(it) }.toMutableList()
            )
        }
    }

    fun setCustomDeckPage(customDeckPage: CustomDeckPage) {
        TODO()
    }

}
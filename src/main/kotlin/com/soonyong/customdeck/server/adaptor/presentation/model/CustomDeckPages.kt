package com.soonyong.customdeck.server.adaptor.presentation.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CustomDeckPages(customDeckPages: Map<Int, CustomDeckPage>) {
    val data: Map<Int, CustomDeckPageDto> = customDeckPages.let { customDeckPageMap ->
        customDeckPageMap.mapValues { CustomDeckPageDto(it.value) }
    }
}

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
class CustomDeckPageDto(customDeckPage: CustomDeckPage) {
    val xCount: Int
    val yCount: Int
    val buttonIds: List<Int>

    init {
        xCount = customDeckPage.xCount
        yCount = customDeckPage.yCount
        buttonIds = customDeckPage.buttons.map { it.id }
    }

}
package com.soonyong.customdeck.server.domain.button

import com.soonyong.customdeck.server.domain.button.model.Button
import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import java.io.File


private const val pageFileName = "page_test_file.txt"
private const val buttonFileName = "button_test_file.txt"

class PageRepositoryTest : FunSpec({

    val buttonRepository = ButtonRepository(buttonFileName)

    test("단순 조회") {
        val pageRepository = PageRepository(pageFileName, buttonRepository)

        shouldThrow<IllegalArgumentException> {
            pageRepository.getCustomDeckPage(1) shouldBe 1
        }
    }

    test("Button: 저장 후 가져오기") {


        val pageRepository = PageRepository(pageFileName, buttonRepository)

        runBlocking {
            pageRepository.setCustomDeckPage(
                CustomDeckPage(
                    id = 1, xCount = 2, yCount = 2, buttons = mutableListOf(
                        Button(0), Button(0), Button(1), Button(id = 2, name = "new Name"),
                    )
                )
            )
        }

        pageRepository.getCustomDeckPage(1).let {
            it.xCount shouldBe 2
            it.id shouldBe 1
            it.yCount shouldBe 2
            it.buttons shouldContainInOrder listOf(Button(0), Button(0), Button(1), Button(id = 2, name = "new Name"))
        }

        val pageRepository2 = PageRepository(pageFileName, buttonRepository)

        pageRepository2.getCustomDeckPage(1).let {
            it.xCount shouldBe 2
            it.id shouldBe 1
            it.yCount shouldBe 2
            it.buttons shouldContainInOrder listOf(Button(0), Button(0), Button(1), Button(id = 2, name = "new Name"))
        }

    }

    afterEach {
        File(pageFileName).delete()
        File(buttonFileName).delete()
    }
})

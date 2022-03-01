package com.soonyong.customdeck.server.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import java.io.File

private const val buttonFileName = "button_test_file.txt"

class ButtonRepositoryTest : FunSpec({

    test("Button: 단순 조회") {
        val buttonRepository = ButtonRepository(buttonFileName)

        buttonRepository.getButton(1).id shouldBe 1
    }

    test("Button: 저장 후 가져오기") {
        val buttonRepository = ButtonRepository(buttonFileName)

        buttonRepository.getButton(1).apply {
            name = "new"
        }.let {
            runBlocking {
                buttonRepository.setButton(it)
            }
        }

        val buttonRepository2 = ButtonRepository(buttonFileName)

        buttonRepository2.getButton(1).let {
            it.id shouldBe 1
            it.name shouldBe "new"
        }

    }

    afterEach {
        File(buttonFileName).delete()
    }

})

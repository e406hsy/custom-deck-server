package com.soonyong.customdeck.server.domain.button

import io.kotest.assertions.fail
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

private const val fileName = "test_file.txt"

class ButtonRepositoryTest : StringSpec({

    "단순 조회"{
        val buttonRepository = ButtonRepository(fileName)

        buttonRepository.getButton(1).block()?.let {
            it.id shouldBe 1
        } ?: fail("should not null")
    }

    "저장 후 가져오기" {
        val buttonRepository = ButtonRepository(fileName)

        buttonRepository.getButton(1).block()?.apply {
            name = "new"
        }?.let {
            buttonRepository.setButton(it)
        } ?: fail("should not null")

        val buttonRepository2 = ButtonRepository(fileName)

        buttonRepository2.getButton(1).block()?.let {
            it.id shouldBe 1
            it.name shouldBe "new"
        } ?: fail("should not null")

    }

    afterEach {
        File(fileName).delete()
    }

})

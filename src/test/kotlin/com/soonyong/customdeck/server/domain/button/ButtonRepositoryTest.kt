package com.soonyong.customdeck.server.domain.button

import io.kotest.assertions.fail
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Thread.sleep

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

        buttonRepository.getButton(1).block()?.let {
            it.name = "new"
            buttonRepository.setButton(it)
        } ?: fail("should not null")

        withContext(Dispatchers.IO) {
            sleep(1000)
        }

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

package com.soonyong.customdeck.server.domain.button

import com.soonyong.customdeck.server.domain.button.model.Button
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import reactor.core.publisher.Mono
import java.io.File

private const val fileName = "test_file.txt"

class ButtonRepositoryTest : StringSpec({

    "단순 조회"{
        val buttonRepository = ButtonRepository(fileName)

        val mono : Mono<Button> = buttonRepository.getButton(1)

        mono.subscribe{
            it.id shouldBe 1
        }
    }

    afterEach {
        File(fileName).delete()
    }

})

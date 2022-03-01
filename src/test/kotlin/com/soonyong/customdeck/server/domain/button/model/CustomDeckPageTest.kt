package com.soonyong.customdeck.server.domain.button.model

import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class CustomDeckPageTest : FunSpec({

    val customDeckPage = CustomDeckPage(2, 4)

    context("index size match") {
        forAll(
            Pair(0, 0),
            Pair(0, 1),
            Pair(0, 2),
            Pair(0, 3),
            Pair(1, 0),
            Pair(1, 1),
            Pair(1, 2),
            Pair(1, 3)
        ) { (xIndex, yIndex) ->
            customDeckPage.getButton(xIndex, yIndex) shouldNotBe null
        }
    }

    context("invalid index value") {
        forAll(
            Pair(-1, 0),
            Pair(0, -1),
            Pair(2, 0),
            Pair(0, 4),
        ) { (xIndex, yIndex) ->
            assertThrows<IllegalArgumentException> {
                customDeckPage.getButton(xIndex, yIndex)
            }
        }
    }


    context("set Button") {

        customDeckPage.setButton(0,1, Button(2) )
        forAll(
            Pair(0, 0),
            Pair(0, 2),
            Pair(0, 3),
            Pair(1, 0),
            Pair(1, 1),
            Pair(1, 2),
            Pair(1, 3)
        ) { (xIndex, yIndex) ->
            customDeckPage.getButton(xIndex, yIndex).id shouldBe 0
        }

        test("get Button") {
            customDeckPage.getButton(0, 1).id shouldBe 2
        }
    }
})

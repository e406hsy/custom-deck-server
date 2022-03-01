package com.soonyong.customdeck.server.domain.button.model

class CustomDeckPage(val xCount: Int, val yCount: Int, buttons: MutableList<Button> = mutableListOf()) {
    private val _buttons: MutableList<Button> = buttons

    val buttons: List<Button>
        get() = _buttons.toList()

    fun setButton(xIndex: Int, yIndex: Int, button: Button) {
        validateIndex(xIndex, yIndex)
        _buttons[getIndex(xIndex, yIndex)] = button
    }

    fun getButton(xIndex: Int, yIndex: Int): Button {
        validateIndex(xIndex, yIndex)
        return _buttons[getIndex(xIndex, yIndex)]
    }

    private fun validateIndex(xIndex: Int, yIndex: Int) {
        if (xIndex >= xCount || yIndex >= yCount) {
            throw IllegalArgumentException("invalid range xCount:$xCount, yCount:$yCount, xIndex:$xIndex, yIndex:$yIndex")
        }
    }

    private fun getIndex(xIndex: Int, yIndex: Int): Int {
        return xIndex * xCount + yIndex
    }
}
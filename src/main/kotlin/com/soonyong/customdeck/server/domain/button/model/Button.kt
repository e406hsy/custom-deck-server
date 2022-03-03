package com.soonyong.customdeck.server.domain.button.model


data class Button(
    val id: Int = 0,
    var type: ButtonType = ButtonType.None,
    var value: MutableMap<String, *> = mutableMapOf<String, Nothing>(),
    var name: String = "button $id"
)

enum class ButtonType {
    KeyBoard, None
}
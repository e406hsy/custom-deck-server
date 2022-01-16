package com.soonyong.customdeck.server.domain.button.model


data class Button(val id: Long, private var name: String = "button $id") {

    fun changeName(newName : String) {
        this.name = newName
    }
}
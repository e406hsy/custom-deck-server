package com.soonyong.customdeck.server.ui

import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import javafx.beans.property.SimpleStringProperty
import org.springframework.context.ApplicationContext
import tornadofx.*

class MasterView : View() {

    private val controller: MyController by di()
    private val input = SimpleStringProperty()
    override val root = vbox {
        hbox {
            text("CustomDeck")
            text("")
        }
        hbox {
            combobox(values = listOf(CustomDeckPage(1, 2, 4))) {
                cellFormat { text = "page ${it.id}" }
            }
        }
        hbox {
            button("button 1") {
                addClass(MasterStyle.tackyButton)
            }
            button("button 2")
            button("button 3")
            button("button 4")
        }
        hbox {
            button("button 5")
            button("button 6")
            button("button 7")
            button("button 8")
        }
    }
}

@org.springframework.stereotype.Controller
class MyController(val applicationContext: ApplicationContext) : Controller() {
    fun writeToDb(inputValue: String) {
        println(applicationContext)
        println(this)
        println("Writing $inputValue to database!")
    }
}
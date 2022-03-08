package com.soonyong.customdeck.server.ui

import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.scene.layout.Priority
import org.springframework.context.ApplicationContext
import tornadofx.*

class MasterView : View() {

    private val controller: MyController by di()
    private val input = SimpleStringProperty()
    override val root = borderpane {
        top = hbox {
            text("CustomDeck") {
                hboxConstraints {
                    margin = Insets(10.0, 20.0, 10.0, 20.0)
                    hGrow = Priority.ALWAYS
                }
            }
            text("Settings")
        }
        center = vbox(10) {
            hbox {
                combobox(values = listOf(CustomDeckPage(1, 2, 4)).asObservable()) {
                    cellFormat { text = "page ${it.id}" }
                }
            }
            gridpane {

                row {
                    button("button 1")
                    button("button 2")
                    button("button 3")
                    button("button 4")
                    children.addClass(MasterStyle.customDeckButton)
                }
                row {
                    button("button 5")
                    button("button 6")
                    button("button 7")
                    button("button 8")
                }
            }
        }
        bottom = hbox {
            text("127.0.0.1:12345") {
                hboxConstraints {
                    margin = Insets(10.0, 20.0, 10.0, 20.0)
                    hGrow = Priority.ALWAYS
                }
            }
        }
    }

    override fun onDock() {
        currentWindow?.setOnCloseRequest {
            if (!primaryStage.isIconified) {
                it.consume()
                primaryStage.isIconified = true
            }
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
package com.soonyong.customdeck.server.ui

import com.soonyong.customdeck.server.domain.button.PageRepository
import com.soonyong.customdeck.server.domain.button.model.CustomDeckPage
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.scene.layout.Priority
import tornadofx.*
import java.lang.Thread.sleep

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
            style {
                backgroundColor += c("ff0000", 1.0)
            }
        }
        center = vbox(10) {
            hbox {
                combobox(values = listOf(CustomDeckPage(1, 2, 4)).asObservable()) {
                    cellFormat { text = "page ${it.id}" }
                    selectionModel.selectFirst()
                    hboxConstraints {
                        margin = Insets(10.0, 20.0, 10.0, 20.0)
                        hGrow = Priority.ALWAYS
                    }
                }
            }
            gridpane {
                padding = Insets(20.0, 10.0, 10.0, 0.0)
                vgap = 10.8
                hgap = 8.0


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
                    children.addClass(MasterStyle.customDeckButton)
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
        runAsync {
            sleep(1000)
        }
    }


}

@org.springframework.stereotype.Controller
class MyController(val pageRepository: PageRepository) : Controller() {

    fun getPages() {
        pageRepository.getCustomDeckPages()
    }

    fun writeToDb(inputValue: String) {
        println(this)
        println("Writing $inputValue to database!")
    }
}
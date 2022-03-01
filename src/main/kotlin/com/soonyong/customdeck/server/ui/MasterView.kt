package com.soonyong.customdeck.server.ui

import javafx.beans.property.SimpleStringProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import tornadofx.*

class MasterView : View() {

    private val controller: MyController by di()
    private val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input)
            }

            button("Commit") {
                action {
                    controller.writeToDb(input.value)
                    input.value = ""
                }
            }
        }
    }
}

@org.springframework.stereotype.Controller
class MyController : Controller() {
    @Autowired
    lateinit var applicationContext: ApplicationContext
    fun writeToDb(inputValue: String) {
        println(applicationContext)
        println(this)
        println("Writing $inputValue to database!")
    }
}
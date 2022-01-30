package com.soonyong.customdeck.server

import com.soonyong.customdeck.server.adaptor.presentation.MasterView
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import tornadofx.launch
import kotlin.reflect.KClass

@SpringBootApplication
class CustomDeckServerApplication : App(MasterView::class) {

    private lateinit var context: ConfigurableApplicationContext //We are going to set application context here

    override fun init() {
        this.context = runApplication<CustomDeckServerApplication>()
        context.autowireCapableBeanFactory.autowireBean(this)

        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = context.getBean(type.java)
            override fun <T : Any> getInstance(type: KClass<T>, name: String): T = context.getBean(name, type.java)
        }
    }

    override fun stop() { // On stop, we have to stop spring as well
        super.stop()
        context.close()
    }
}

fun main(args: Array<String>) {
    System.setProperty("java.awt.headless", "false")

    launch<CustomDeckServerApplication>(args)
}

package javafx

import javafx.application.Application
import tornadofx.App

class PianoApp: App(PianoView::class)

fun main(args: Array<String>) {
    Application.launch(PianoApp::class.java, *args)
}
package javafx

import core.*
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import tornadofx.Dimension.LinearUnits.px
import tornadofx.*

class PianoView: View() {

    override var root = BorderPane()

    private val whiteButtonAmount = 8
    private val blackButtonAmount = 5

    private val initialWhiteColor = c("f7f7f7")
    private val pressedWhiteColor = c("b5b5b5")
    private val hoverWhiteColor = c("D0D0D0")

    private val initialBlackColor = c("000000")
    private val pressedBlackColor = c("707070")
    private val hoverBlackColor = c("393939")

    private val whiteWidth = 70.0
    private val blackWidth = 35.0

    private var buttonPosition = 0.0

    private val controller = PianoController()

    init {
        title = "Piano"
        with(root) {
            prefWidth = 600.0//buttonAmount * 70.0
            prefHeight = 400.0
            top {
                vbox {
                    alignment = Pos.TOP_CENTER
                    menubar {
                        menu("Options") {
                            item("Stop playing sounds") {
                                action {
                                    controller.stopPlaying()
                                }
                            }
                        }
                    }
                    label {
                        text = "Superb Piano Keyboard"
                        style {
                            font = Font.font("Arial", 20.0)
                        }
                    }
                    hbox {
                        alignment = Pos.CENTER
                        checkbox {
                            text = "Change octave"
                            isSelected = controller.alterOctave
                            style {
                                faintFocusColor = Color.TRANSPARENT
                            }
                            action {
                                controller.toggleOctaveAlteration()
                            }
                        }
                        spacing = 20.0
                        checkbox {
                            text = "Toggle key entering playing"
                            isSelected = controller.triggerUponHovering
                            style {
                                faintFocusColor = Color.TRANSPARENT
                            }
                            action {
                                controller.toggleHovering()
                            }
                        }
                    }
                }
            }
            center {
                anchorpane{
                    for (i in 0 until whiteButtonAmount) {
                        val key = Key(Colour.WHITE, i)
                        add(createButton(key))
                    }
                    buttonPosition = whiteWidth - blackWidth / 2
                    for (i in 0 until blackButtonAmount) {
                        val key = Key(Colour.BLACK, i)
                        add(createButton(key))
                        }
                    }
                }
            }
        }
    private fun createButton(key: Key): Rectangle {
        val rect = Rectangle()
        with (rect) {
            if (key.colour == Colour.WHITE) {
            layoutX = buttonPosition
            width = whiteWidth
            buttonPosition += width
            height = 270.0
            fill = initialWhiteColor
            } else {
                layoutX = buttonPosition
                if (key.ID == 1)
                    buttonPosition += 70
                width = blackWidth
                buttonPosition += 70
                height = 180.0
                fill = initialBlackColor
            }
            style {
                stroke = Color.BLACK
                strokeWidth = Dimension(0.8, px)
            }
        }
        instantiateCallbacks(rect, key)
        return rect
    }
    private fun instantiateCallbacks(rect: Rectangle, key: Key) {
        with(rect) {
                setOnMousePressed {
                    fill = if (key.colour == Colour.BLACK)
                        pressedBlackColor
                    else
                        pressedWhiteColor
                    controller.playCorrectNote(key)
                }

                setOnMouseEntered {
                    fill = if (key.colour == Colour.BLACK)
                        hoverBlackColor
                    else
                        hoverWhiteColor
                    controller.playWhilePressed(key)
                }
                setOnMouseExited {
                    fill = if (key.colour == Colour.BLACK)
                        initialBlackColor
                    else
                        initialWhiteColor
                }
                setOnMouseReleased {
                    fill = if (key.colour == Colour.BLACK)
                        initialBlackColor
                    else
                        initialWhiteColor
                }
        }
    }
}

package javafx

import core.Colour
import core.Key
import javafx.scene.media.AudioClip
import tornadofx.Controller
import java.io.File

class PianoController: Controller() {

    private val whiteSounds = mutableListOf<AudioClip>()
    private val blackSounds = mutableListOf<AudioClip>()

    var triggerUponHovering = false
    var alterOctave = false


    init {
        File("src/main/resources/").walk().filter { it.extension == "wav" }.forEach {
            if (it.name.contains("White"))
                whiteSounds.add(AudioClip(it.toURI().toString()))
            else
                blackSounds.add(AudioClip(it.toURI().toString()))
        }
    }

    fun playCorrectNote(key: Key) {
        val correctNote = when {
            key.colour == Colour.WHITE && !alterOctave ->
                whiteSounds[key.ID]
            key.colour == Colour.WHITE && alterOctave ->
                whiteSounds[key.ID + whiteSounds.size / 2]
            key.colour == Colour.BLACK  && !alterOctave->
                blackSounds[key.ID]
            else -> blackSounds[key.ID + blackSounds.size / 2]
        }
        correctNote.play()
    }

    fun toggleHovering() {
        triggerUponHovering = !triggerUponHovering
    }
    fun toggleOctaveAlteration() {
        alterOctave = !alterOctave
    }
    fun playWhilePressed(key: Key) {
        if (triggerUponHovering)
            playCorrectNote(key)
    }

    fun stopPlaying() = whiteSounds.forEach { it.stop() }
}
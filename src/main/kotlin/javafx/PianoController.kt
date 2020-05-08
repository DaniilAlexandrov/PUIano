package javafx

import core.Colour
import core.Key
import javafx.scene.media.AudioClip
import tornadofx.Controller

class PianoController: Controller() {

    private val whiteSounds = mutableListOf<AudioClip>()
    private val blackSounds = mutableListOf<AudioClip>()

    var triggerUponHovering = false
    var alterOctave = false

    init {
        fillList(whiteSounds, black = false, altered = true)
        fillList(whiteSounds, black = false, altered = false)
        fillList(blackSounds, black = true, altered = true)
        fillList(blackSounds, black = true, altered = false)
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

    fun stopPlaying() {
        blackSounds.forEach { it.stop() }
        whiteSounds.forEach { it.stop() }
    }

    private fun  fillList(targetList: MutableList<AudioClip>, black: Boolean, altered: Boolean) {
        val upperConstraint = if (black) 5 else 8
        for (i in 1..upperConstraint) {
            val url = when {
                black && !altered -> this.javaClass.classLoader.getResource("Sounds/Black${i}.wav")
                black && altered -> this.javaClass.classLoader.getResource("Sounds/AlteredBlack${i}.wav")
                !black && !altered -> this.javaClass.classLoader.getResource("Sounds/White${i}.wav")
                else -> this.javaClass.classLoader.getResource("Sounds/AlteredWhite${i}.wav")
            }
            targetList.add(AudioClip(url!!.toString()))
        }
    }
}
package javafx

import javafx.scene.media.AudioClip
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class PianoTest {

    /*
    For correct functionality in order not to receive outOfBoundsException 26
    sounds altogether should be present.
     */
    @Test
    fun soundListFullnessTest() {
        val pc = PianoController()
        assertEquals(26, pc.whiteSounds.size + pc.blackSounds.size)
    }

    /*
    Making sure that 8 white sounds are added upon request.
     */
    @Test
    fun fillListTestWhite() {
        val list = mutableListOf<AudioClip>()
        PianoController().fillList(list, black = false, altered = false)
        assertEquals(8, list.size)
    }

    /*
    Making sure that 8 white sounds are added upon request.
     */
    @Test
    fun fillListTestWhiteAltered() {
        val list = mutableListOf<AudioClip>()
        PianoController().fillList(list, black = false, altered = true)
        assertEquals(8, list.size)
    }

    /*
    Making sure that 5 black sounds are added upon request.
     */
    @Test
    fun fillListTestBlack() {
        val list = mutableListOf<AudioClip>()
        PianoController().fillList(list, black = true, altered = false)
        assertEquals(5, list.size)
    }

    /*
    Making sure that 5 black sounds are added upon request.
     */
    @Test
    fun fillListTestBlackAltered() {
        val list = mutableListOf<AudioClip>()
        PianoController().fillList(list, black = true, altered = true)
        assertEquals(5, list.size)
    }
}
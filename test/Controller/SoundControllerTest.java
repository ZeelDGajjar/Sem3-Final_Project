package Controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SoundController logic.
 * These tests avoid JavaFX MediaPlayer and only test non-JavaFX logic.
 */
public class SoundControllerTest {

    @Test
    public void testInitialMuteState() {
        SoundController sc = new SoundController();
        assertFalse(sc.isMuted(), "Sound should start unmuted");
    }

    
    @Test
    public void testToggleMute() {
        SoundController sc = new SoundController();

        sc.toggleMute();
        assertTrue(sc.isMuted(), "toggleMute() should set muted = true");

        sc.toggleMute();
        assertFalse(sc.isMuted(), "toggleMute() should set muted = false again");
    }

    @Test
    public void testPlayBackgroundMusicDoesNotCrash() {
        SoundController sc = new SoundController();

        // Should NOT throw an exception even if files are missing
        assertDoesNotThrow(() -> sc.playBackgroundMusic());
    }

    @Test
    public void testStopBackgroundMusicDoesNotCrash() {
        SoundController sc = new SoundController();
        assertDoesNotThrow(() -> sc.stopBackgroundMusic());
    }
    
    @Test
    public void testPlaySoundEffectDoesNotCrash() {
        SoundController sc = new SoundController();

        // Should NOT throw an exception even if files are missing
        assertDoesNotThrow(() -> sc.playSoundEffect());
    }
}

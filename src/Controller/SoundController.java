/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Manages all audio of the project 
 * @author Vedika 
 */
class SoundController {
    private MediaPlayer backgroundMusic; 
    private MediaPlayer music; 
    private double volume = 0.5; 
    private boolean muted = false; 
   
    /**
     * Plays background music on loop.
     */
    public void playBackgroundMusic() {
        try {
            Media media = new Media(getClass().getResource("/Music/BackgroundMusic.mp3").toExternalForm());
            backgroundMusic = new MediaPlayer(media);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
        } catch (Exception e) {
            System.out.println("Error loading background music: " + e.getMessage());
        }
    }
    
    /**
     * Stops currently playing background music, if any
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    /**
     * Plays a short sound effect (like level up or hit).
     */
    public void playSoundEffect() {
        try {
            Media media = new Media(getClass().getResource("/Music/soundEffect.mp3").toExternalForm());
            music = new MediaPlayer(media);
            music.play();
        } catch (Exception e) {
            System.out.println("Error loading sound effect: " + e.getMessage());
        }
    }
    
      /**
     * Mutes or unmutes all game audio.
     */
    public void toggleMute() {
        muted = !muted;

        if (backgroundMusic != null) {
            backgroundMusic.setMute(muted);
        }
        if (music != null) {
            music.setMute(muted);
        }
    }
    
    /**
     * Returns whether the game audio is currently muted
     * @return true if all audio is muted, false otherwise
     */
    public boolean isMuted() {
        return muted;
    }
}



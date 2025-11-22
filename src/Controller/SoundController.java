/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Vedika 
 */
class SoundController {
    private MediaPlayer backgroundMusic; 
    private MediaPlayer music; 
    
    //the filePath of the music 
    //i put it inside of view for now //is inside of view for now 
    
    //for now i add the one i added in my assigment but need to change 
    /**
     * Plays background music on loop.
     */
    public void playBackgroundMusic() {
        try {
            Media media = new Media(getClass().getResource("/View/slideshow.mp3").toExternalForm());
            backgroundMusic = new MediaPlayer(media);
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
        } catch (Exception e) {
            System.out.println("Error loading background music: " + e.getMessage());
        }
    }
    
    /**
     * Stops background music.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    /**
     * Plays a short sound effect (like level up or hit).
     */
    public void playSoundEffect(String filePath) {
        try {
            Media media = new Media(getClass().getResource(filePath).toExternalForm());
            music = new MediaPlayer(media);
            music.play();
        } catch (Exception e) {
            System.out.println("Error loading sound effect: " + e.getMessage());
        }
    }
}


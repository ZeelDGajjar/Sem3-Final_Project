/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.animation.Timeline;
import Model.GameModel;
//import View.GameView;

/**
 *
 * @author zeelg
 */
public class GameController {
    private GameModel model;
   // private GameView view;
    private SoundController soundController;
    private Timeline countdownTimer;
    
    public GameController(GameModel model){
        this.model = model;
        //this.view = view;
    }
    
    /**
     * Handles the speed and angle values given by the user.
     * 
     * @param speed The given double speed
     * @param angle The given double angle
     */
    public void handleUserInput(double speed, double angle) {
        
    }
    
    /**
     * Setting the speed for user input
     * 
     * @param speed The given double speed
     */
    public void setSpeed(double speed) {
    }
    
    /**
     * Setting the direction(angle)
     * 
     * @param angle The given double angle
     */
    public void setDirection(double angle) {}
    
    public void handleLaunch() {}
    
    public void showResult() {}
    
    public void handleResetLevel() {}
    
    public void handleNextLevel() {}
    
    public void handleRestartGame() {}
    
    public void startCountdown() {}
    
    public boolean checkCountdown() {
        return true;
    }
    
    public void forceEndCountdown() {
    }
    
    public void onModelUpdate() {}
}

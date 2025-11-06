/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import java.util.List;

/**
 *
 * @author Vedika
 */
class GameModel {
       private int currentLevel; 
       private int maxLevels;  //make final and set a fix value 
       private double difficultyFactor; 
       
       //time 
       private long levelStartTime; 
       private long levelTimeLimit; //seconds allowed per level 
       
       //simulation objects
       private Projectile projectile; 
       private List<Planet> planets; 
       
       
       private GameState gameState;
    
    // Constructor that receives the GameState
    public GameModel(GameState gameState) {
        this.gameState = gameState;
        this.currentLevel = 1; 
        this.difficultyFactor = 1.0;
    }

     /** Start a level by updating timers and dynamic difficulty */
    public void startLevel() {
        System.out.println("Level started.");
         this.levelStartTime = System.currentTimeMillis();
        this.difficultyFactor = 1.0 + (currentLevel - 1) * 0.5;
        gameState.setCurrentLevel(currentLevel);
    }
    
   
    
    
    public GameState getGameState() {
        return gameState;
    }
}


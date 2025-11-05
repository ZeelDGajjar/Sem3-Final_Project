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
       private int maxLevels; 
       private double difficultyFactor; 
       private long levelStartTime; 
       private long levelTimeLimit; 
       private Projectile projectile; 
       private List<Planet> planets; 
       
       
       private GameState gameState;
    
    // Constructor that receives the GameState
    public GameModel(GameState gameState) {
        this.gameState = gameState;
    }

    // Example placeholder methods
    public void startLevel() {
        System.out.println("Level started.");
    }

    public GameState getGameState() {
        return gameState;
    }
}


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
       private Planet targetPlanet; 
       private Trajectory lastTrajectory; 
       
       
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
    
      /**
     * Called when rocket reaches target planet
     */
    private void handleSuccessfulHit() {
        int pointsEarned = (int) (100 * difficultyFactor);
        gameState.updateScore(pointsEarned);

        // Unlock next level if possible
        if (currentLevel < maxLevels) {
            currentLevel++;
        } else {
            gameState.setZombied(true); // winning final level = game finished
        }
    }
    
       /**
     * Called when rocket fails: track failure reason and game over if planets/zombies eat you :)
     */
    private void handleMiss(Trajectory trajectory) {

        boolean timedOut = isTimeUp();
        if (timedOut) {
            gameState.setZombied(true);
            trajectory.setFailureReason("Timeout: Zombie planets caught you!");
        } else {
            trajectory.setFailureReason("Missed the target planet!");
        }
    }
   
     /**
     * Track the countdown
     * called regularly in controller to update UI
     */
    public long getRemainingLevelTime() {
        long elapsedSec = (System.currentTimeMillis() - levelStartTime) / 1000;
        long remaining = levelTimeLimit - elapsedSec;

        if (remaining <= 0) {
            gameState.setZombied(true);
            return 0;
        }
        return remaining;
    }
    
   
    //getter and setters 
    public GameState getGameState() {
        return gameState;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public double getDifficultyFactor() {
        return difficultyFactor;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
        if (!planets.isEmpty()) {
            this.targetPlanet = planets.get(currentLevel - 1);
        }
    }

    public Planet getTargetPlanet() {
        return targetPlanet;
    }

    public Trajectory getLastTrajectory() {
        return lastTrajectory;
    }

    public long getLevelTimeLimit() {
        return levelTimeLimit;
    }

    public void setLevelTimeLimit(long limitSeconds) {
        this.levelTimeLimit = limitSeconds;
    }

    private boolean isTimeUp() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



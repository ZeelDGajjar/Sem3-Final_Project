/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import java.util.ArrayList;
import java.util.List;

/**
 * Core game logic model : 
 * Handles level progression, physics simulation , and timer logic
 * Coordinates with GameState to track player progress 
 * Notifies observers (views/ controllers) when the state changes
 * @author Vedika
 */
       public class GameModel {
       private int currentLevel; 
       private final int maxLevels = 5;  //make final and set a fix value 
       private double difficultyFactor; 
       
       //time 
       private long levelStartTime; 
       private long levelTimeLimit = 30 ; //seconds allowed per level 
       
       //simulation objects
       private Projectile projectile; 
       private List<Planet> planets; 
       private Planet targetPlanet; 
      // private Trajectory lastTrajectory; 
       
       //observer 
       private List<GameObserver> observers; 
       
       private GameState gameState;
       private boolean isZombied; 
    
    // Constructor that receives the GameState
    public GameModel(GameState gameState) {
        this.gameState = gameState;
        this.currentLevel = 1; 
        this.difficultyFactor = 1.0;
        this.observers = new ArrayList<>();
    }

     /** Start a level by updating timers and dynamic difficulty */
    public void startLevel() {
        System.out.println("Level " + currentLevel +  "started.");
         this.levelStartTime = System.currentTimeMillis();
        this.difficultyFactor = 1.0 + (currentLevel - 1) * 0.5;
        this.isZombied = false; 
        gameState.setCurrentLevel(currentLevel);
    }
    
    /**
     * advance to next level or ends game if last level reached
     */
    public void advanceLevel() {
        if (currentLevel < maxLevels) {
            currentLevel++; 
            updateTargetPlanet();
            startLevel();
        }
        else {
            isZombied = true; //game finised 
            gameState.setZombied(true);
        }
        notifyObservers();
    }
    
    public void resetLevel() {
        projectile = null; 
        lastProjectile = null; 
        startLevel();
    }
     /**
      * check if this is the final level 
      */
    public boolean isFinalLevel() {
        return currentLevel == maxLevels; 
    }
    
    //about time 
    public void startLevelTimer() {
        
    }
    
    public void isLevelTimeUp() {
        
    }
    
    public void getRemainingLevelTime(long remainingLevelTime) {
        
    }
    
    
     /**
     * Run physics simulation → updates trajectory and determines success/failure.
     */
    public Trajectory simulateLaunch(double speed, double angleDegrees) {

        gameState.addAttempts();
        projectile = new Projectile(speed, angleDegrees);

        Trajectory trajectory = PhysicsUtil.calculateTrajectoryPoint(projectile, difficultyFactor);
        this.lastTrajectory = trajectory;

        boolean hit = CollisionUtil.checkCollision(trajectory, targetPlanet);

        if (hit) {
            handleSuccessfulHit();
            trajectory.setFailureReason(null);
        } else {
            handleMiss(trajectory);
        }

        return trajectory;
    }
    
    public String checkFailure(Trajectory traj) {
        return "ok"; //for now
    }
    
    public void updateGameState(boolean success) {
        
    }
    
    public void resetGame() {
        
    }
    
    public void addObservers(GameObserver observer) {
        
    }
    
    public void notifyObservers() {
        
    }
    
    //method to find the trajectory result 
    public void processTrajectoryResult(boolean hit, Trajectory traj) {
    this.lastTrajectory = traj;

    if (hit) {
        handleSuccessfulHit();
    } else {
        handleMiss(traj);
    }
}
    //method to udpate the target planet when the level changes 
    private void updateTargetPlanet() {
    if (planets != null && currentLevel - 1 < planets.size()) {
        targetPlanet = planets.get(currentLevel - 1);
    }
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
            updateTargetPlanet(); 
            startLevel();
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
   
     public boolean isTimeUp() {
        return getRemainingLevelTime() <= 0;
    
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
    
    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }
    
    public void setLastTrajectory(Trajectory trajectory) {
        this.lastTrajectory = trajectory;
    }

}


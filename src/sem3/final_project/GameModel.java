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
       private Trajectory lastTrajectory; //most recent launch result 
       
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
        lastTrajectory = null; 
        startLevel();
    }
     /**
      * check if this is the final level 
      */
    public boolean isFinalLevel() {
        return currentLevel == maxLevels; 
    }
    
    //Time methods 
    
    /**
     * Starts or restarts the level timer 
     */ 
    public void startLevelTimer() {
        this.levelStartTime = System.currentTimeMillis();
    }
    
    /**
     * checks if level time is up
     */
    public boolean isLevelTimeUp() {
        return getRemainingLevelTime() <= 0; 
    }
    
    /**
     * return remaining seconds for this level 
     * @param remainingLevelTime 
     */
    public long getRemainingLevelTime(long remainingLevelTime) {
        long elapsSec = (System.currentTimeMillis() - levelStartTime) / 1000; 
        long remaining = levelTimeLimit - elapsSec; 
        if (remaining <= 0) {
            gameState.setZombied(true);
            isZombied = true; 
            return 0; 
        }
        return remaining; 
    }
    
    
     /**
     * Run physics simulation → updates trajectory and determines success/failure.
     */
    public Trajectory simulateLaunch(double speed, double angleDegrees) {
       
        if (targetPlanet == null ) {
            System.err.println("No target planet set. Cannot simuulate launch");
            return null; 
        }
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
        notifyObservers();
        return trajectory;
    }
    
    //Determines and returns failure reason text for UI 
    public String checkFailure(Trajectory traj) {
        if (traj == null) return "No trajectory.";
        if (gameState.isZombied()) return "Game over!";
        if (traj.getFailureReason() != null) return traj.getFailureReason();
        return "Success!";
    } 
    
    //** Updates GameState depending on whether the player succeeded
    public void updateGameState(boolean success) {
        if (success) {
        int pointsEarned = (int) (100 * difficultyFactor);
            gameState.updateScore(pointsEarned);
        } else {
            gameState.updateScore(-10); // penalty for failure
        }
        notifyObservers();
    }
    
    /**
     * resets the entire game back to level 1
     */
    public void resetGame() {
        currentLevel = 1; 
        difficultyFactor = 1.0; 
        isZombied = false; 
        gameState.resetAll();
        updateTargetPlanet();
        startLevel();
    }
    
    /** 
     * observer pattern : register observer
     * @param observer 
     */
    public void addObservers(GameObserver observer) {
        if (!observers.contains(observer));
        observers.add(observer);
    }
    
    /**
     * notify all observers that the game state has changed
     */
    public void notifyObservers() {
        for (GameObserver obs : observers) {
            obs.onGameStateUpdated(this); //error 
        }
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
    
      /**
     * Called when rocket reaches target planet
     */
    private void handleSuccessfulHit() {
        int pointsEarned = (int) (100 * difficultyFactor);
        gameState.updateScore(pointsEarned);

        // Unlock next level if possible
        if (isFinalLevel()) {
            System.out.println("All levels complete!");
            isZombied = true; 
            gameState.setZombied(true);
        } else {
            advanceLevel();
        }
    }
    
       /**
     * Called when rocket fails: track failure reason and game over if planets/zombies eat you :)
     */
    private void handleMiss(Trajectory trajectory) {
        
        if (isLevelTimeUp()) {
            gameState.setZombied(true);
            trajectory.setFailureReason("Timeout: Zombie planets caught you!");
        } else {
            gameState.updateScore(-10);
            trajectory.setFailureReason("Missed the target planet!");
        }
    }
   
    //method to udpate the target planet when the level changes 
    private void updateTargetPlanet() {
    if (planets != null && currentLevel - 1 < planets.size()) {
        targetPlanet = planets.get(currentLevel - 1);
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


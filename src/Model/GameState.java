/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Tracks the player's progress : score , attempts, timers and game failure state
 * Handles updating and retrieving game state information.
 * @author Vedika
 */
public class GameState {
    private int score; 
    private int attempts; 
    private long totalPlayTimeSec;  
    
    private int currentLevel; 
    private int maxLevelReached;
    
    private boolean isZombied;
    
    /**
     * Creates a new gameState with all values initialized to defaults
     */
    public GameState() {
        resetAll(); 
    }
    
    /**
     * Resets all game state values to their initial values
     */
    public void resetAll() {
        this.score = 0; 
        this.attempts = 0; 
        this.totalPlayTimeSec = (long) 0.0;
        this.isZombied = false; 
        this.maxLevelReached = 1; 
    }
    
    // SCORE METHODS 
    
    /**
     * Adds points to the player's score. 
     * prevents the score from dropping below zero
     * @param pointsEarned number of points to add (can be negative)
     */
    public void updateScore(int pointsEarned ) {
        if (pointsEarned > 0) {
            this.score += pointsEarned; 
        } else if (pointsEarned < 0) {
            //handles the penality safely (prevents the score to go below 0)
            this.score = Math.max(0, this.score + pointsEarned);
        }
        //if pointsEarned == 0, do nothing 
    }
    
    public int getScore() {
        return score; 
    }
    
    //LEVEL METHODS  
    
    /**
     * Sets the current level and updates maxLevel is needed
     * @param level  level to set (the minimum allowed is 1)
     */
    public void setCurrentLevel(int level) {
        this.currentLevel = Math.max(1,level);
        
        if (level > maxLevelReached) {
            maxLevelReached = level; 
        }
    }
    
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public int getMaxLevelReached() {
        return maxLevelReached;
    }
    
    //ATTEMPTS METHODS 
    
    /**
     * Increments the number of attempts, whenever a projectile is launched
     */
    public void addAttempts() {
        this.attempts++; 
    }
    
    public int  getAttempts() {
        return attempts;
    }
    
    //PLAY TIMING 
    
     /**
     * Adds time spent in the current level(must be positive)
     * @param durationSeconds time duration in seconds 
     */
    public void addPlayTime(long durationSeconds) {
        if (durationSeconds > 0) {
            this.totalPlayTimeSec += durationSeconds; 
        }
    }
    
     public double getTotalPlayTimeSeconds() {
        return totalPlayTimeSec;
    }
     
     //GAME FAILURE STATE (ZOMBIED)
    
    /**
     * Sets the game failure state
     * @param zombied  true if the player has failed the level 
     */
    public void setZombied (boolean zombied) {
        this.isZombied = zombied; 
    }

    /**
     * 
     * @return true if the player has failed 
     */
    public boolean isZombied() {
        return isZombied;
    }
    
    /**
     * Determines if the game is over based on the failure state
     * @return true if the game is over (player failed)
     */
    public boolean isGameOver() {
        return isZombied; 
    }
}
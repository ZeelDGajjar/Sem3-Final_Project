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
    private int level; 
    private int attempts; 
    private long totalPlayTimeSec; //total time spent playing 
    
    private int currentLevel; 
    private int maxLevelReached;
    
    private boolean isZombied;
    
    public GameState() {
        resetAll(); //initialize all values 
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
    
    // Score methods 
    
    /**
     * Adds points to the player's score. 
     * @param points points to add
     */
    public void updateScore(int pointsEarned ) {
        if (pointsEarned > 0) {
            this.score += pointsEarned; 
        } else if (pointsEarned < 0) {
            //prevent the score from going below 0, so handle the penelalites safely
            this.score = Math.max(0, this.score + pointsEarned);
        }
        //if pointsEarned == 0, do nothing 
    }
    
    public int getScore() {
        return score; 
    }
    
    //level methods 
    
    /**
     * Sets the current level and updates maxLevel is needed
     * @param level  level to set (the minimum is 1)
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
    
    //attempts methods 
    
    /**
     * add one attempt every time a projectile is launched
     */
    public void addAttempts() {
        this.attempts++; 
    }
    
    public int  getAttempts() {
        return attempts;
    }
    
    //Play time methods 
    
     /**
     * Adds time spend during launch or level ( controller will track)
     * @param duration 
     */
    public void addPlayTime(long durationSeconds) {
        if (durationSeconds > 0) {
            this.totalPlayTimeSec += durationSeconds; 
        }
    }
    
     public double getTotalPlayTimeSeconds() {
        return totalPlayTimeSec;
    }
     
     //Game State / Failure methods 
    
    /**
     * Sets the game failure (true if player failed level)
     * @param zombied Whether player has failed 
     */
    public void setZombied (boolean zombied) {
        this.isZombied = zombied; 
    }

    public boolean isZombied() {
        return isZombied;
    }
    
    /**
     * checks if the game is over (based on failure state for now)
     * @return true if the player failed 
     */
    public boolean isGameOver() {
        return isZombied; 
    }
}
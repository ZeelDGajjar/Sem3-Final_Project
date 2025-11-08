/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;


/**
 * Tracks the player's progress : score , attempts, timers and game failure state
 * Handles updating and retrieving game state information.
 * @author Vedika
 */
class GameState {
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
    
    /**
     * Adds points to the player's score. 
     * @param points points to add
     */
    public void UpdateScore(int pointsEarned ) {
        if (pointsEarned > 0) {
            this.score += pointsEarned; 
        } else if (pointsEarned < 0) {
            //prevent the score from going below 0, so handle the penelalites safely
            this.score = Math.max(0, this.score + PointsEarned);
        }
    }
    
    /**
     * method used to move forward 
     * called by the gameModel to advance a level
     * @param level 
     */
    public void setCurrentLevel(int level) {
        this.currentLevel = Math.max(1,level);
        if (level > maxLevelReached) {
            maxLevelReached = level; 
        }
    }
    
    /**
     * add one attempt every time a projectile is launched
     */
    public void addAttempts() {
        this.attempts++; 
    }
    
    public int  getAttempts() {
        return attempts;
    }
    
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
    
    /**
     * Called when a player fails the level bu timeout or crashing
     * @return 
     */
    public void setZombied (boolean zombied) {
        this.isZombied = zombied; 
    }
    
    
    /// Getters 
    public int getScore() {
        return score; 
    }
    
    
    
    public int getCurrentLevel() {
        return currentLevel;
    }

    
       public int getMaxLevelReached() {
        return maxLevelReached;
    }


    public boolean isZombied() {
        return isZombied;
    }

    public void updateScore(int pointsEarned) {
    // Only add positive points
    if (pointsEarned > 0) {
        this.score += pointsEarned;
    }
    // Optionally, you could handle negative points (penalties)
    else if (pointsEarned < 0) {
        // Prevent score going below 0
        this.score = Math.max(0, this.score + pointsEarned);
    }
    // If pointsEarned is 0, do nothing
    }
}

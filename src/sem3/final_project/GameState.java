/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;


/**
 *
 * @author Vedika
 */
class GameState {
    private int score; 
    private int attempts; 
    private long totalPlayTimeSec; //total time spent playing 
    
    private int currentLevel; 
    private int maxLevelReached;
    
    private boolean isZombied;
    
    public GameState() {
        resetAll();
    }
    
    public void resetAll() {
        this.score = 0; 
        this.attempts = 0; 
        this.totalPlayTimeSec = (long) 0.0;
        this.isZombied = false; 
        this.maxLevelReached = 1; 
    }
    
    public void UpdateScore(int points ) {
        if (points > 0) {
            this.score += points; 
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
    
    /**
     * Called when a player fails the level bu timeout or crashing
     * @return 
     */
    public void setZombied (boolean zombied) {
        this.isZombied = zombied; 
    }
    
    
    public boolean isGameOver() {
        return isZombied ; //** just for now 
    }
    
    /// Getters 
    public int getScore() {
        return score; 
    }
    
    public double getTotalPlayTimeSeconds() {
        return totalPlayTimeSec;
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
    
    
}

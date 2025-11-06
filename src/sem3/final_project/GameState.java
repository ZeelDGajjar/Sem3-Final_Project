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
    private int level; 
    private int attempts; 
    private long totalPlayTimeSec; //total time spent playing 
    private int currentLevel; 
    private boolean isZombied; 
    private int maxLevelReached;
    
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
    
    public void trackScore() {
        
    }
    
    public void setLevel() {
        
    }
    
    public void addAttempts() {
        
    }
    
    public int  getAttempts() {
        return attempts;
    }
    
    public void resetGame() {
        
    }
    
    public boolean isGameOver() {
        return true ; //** just for now 
    }
    
    public void addPlayTime(long duration ) {
        
    }
    
    
    //add more getters and setters
    public int getScore() {
        return score; 
    }
    
    public double getTotalPlayTimeSeconds() {
        return totalPlayTimeSec;
    }
    
    public getCurrentLevel() {
        return currentLevel;
    }
    
    
}

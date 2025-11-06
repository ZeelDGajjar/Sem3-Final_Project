/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import static com.sun.webkit.perf.PerfLogger.resetAll;

/**
 *
 * @author Vedika
 */
class GameState {
    private int score; 
    private int level; 
    private int attempts; 
    private long totalPlayTime; //total time spent playing 
    private int currentLevel; 
    private boolean isZombied; 
    private int maxLevelReached;
    
    public GameState() {
        resetAll();
    }
    
    public void resetAll() {
        this.score = 0; 
        this.attempts = 0; 
        this.totalPlayTime = (long) 0.0;
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
        return 10; //**just for now 
    }
    
    public void resetGame() {
        
    }
    
    public boolean isGameOver() {
        return true ; //** just for now 
    }
    
    public void addPlayTime(long duration ) {
        
    }
    
    public long getTotalPlayTime() {
        return 10;  //just for now 
    }
}

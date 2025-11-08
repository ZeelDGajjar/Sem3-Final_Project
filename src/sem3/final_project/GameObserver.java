/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

/**
 * The GameObserver interface follows the Observer design pattern.
 * 
 * Any class (for example, a JavaFX controller or UI manager) that wants to be 
 * notified of changes in the game model (like score updates, level progress, 
 * or game over events) should implement this interface.
 *
 * The GameModel will call these methods to keep observers up to date.
 
 * @author Vedika
 */


 public interface GameObserver {
     
     /**
 * Called whenever the GameModel updates the GameState
 * ( for example, after a successful hit, score changes, or timer update ) 
 * @param newState the updated GameState object
 * @author Vedika
 */
    void onGameStateChanged(GameState newState);

    
    /**
     * Called when the player advances to a new level 
     * @param newLevel the current level number
     */
    default void onLevelChanged(int newLevel) {
        // default : do nothing 
    }

    /**
     * Called when the game ends (either by winning or losing)
     * @param isZombied 
     */
    default void onGameOver(boolean isZombied) {
        // optional implementation
    }
}

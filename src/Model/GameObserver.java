/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**INTERFACE
 * /**
 * Any class (for example, a JavaFX controller or UI manager) that wants to be 
 * notified of changes in the game model (like score updates, level progress, 
 * or game over events) should implement this interface.
 *
 * The GameModel will call these methods to keep observers up to date.
 
 * @author Vedika
 */

interface GameObserver {
    
    /**
    * Called whenever the GameModel updates the GameState
    * ( for example, after a successful hit, score changes, or timer update )
    * notification for the state
    * @param newState the updated GameState object
    * @author Vedika
    */
    void onGameStateChanged(GameState newState);
    
    
    /**
     * Called to notify the observer that the game model has been updated.
     * This default implementation does nothing 
     * Classes implementing this interface can override this method 
     * to perform actions when the game state changes 
     * notification for the full model 
     * @param model the gameModel instance that has changed
     */
    default void update(GameModel model) {
        
    }
    
    /**
     * Called when the player advances to a new level 
     * @param newLevel the current level number
     */
    default void onLevelChanged(int newLevel) {
        // default : do nothing 
    }

    /**
     * Called when the game ends (either by winning or losing)
     * @param isZombied true if the player lost (e.g timeout or failure),
     * false if they won the final level
     */
    default void onGameOver(boolean isZombied) {
        // optional implementation
    }
    
    /**
     * Called periodically to update the remaining seconds
     * @param reamainingSeconds how many seconds are left in the UI
     */
    default void onTimerUpdate( long reamainingSeconds) {
        //default
    }
}


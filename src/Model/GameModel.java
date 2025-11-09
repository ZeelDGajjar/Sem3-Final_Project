
package Model;

/**
 *
 * @author Vedika
 */
public class GameModel {
    private GameState gameState;
    
    // Constructor that receives the GameState
    public GameModel(GameState gameState) {
        this.gameState = gameState;
    }

    // Example placeholder methods
    public void startLevel() {
        System.out.println("Level started.");
    }

    public GameState getGameState() {
        return gameState;
    }
}


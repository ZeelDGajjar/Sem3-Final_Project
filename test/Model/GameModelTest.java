package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GameModel class
 * This class tests core game logic, including level progression, 
 * resetting levels/game, failure detection, and updating the game state.
 */
class GameModelTest {

    private GameState gameState;
    private GameModel gameModel;

     /**
     * Sets up before starting the tests
     * Initializes a GameState and GameModel with minimal planets for testing.
     */
    @BeforeEach
    void setUp() {
        gameState = new GameState();
        gameModel = new GameModel(gameState);

        // Minimal planets for levels
        List<Planet> planets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            planets.add(new Planet("Planet" + i, 0, 0, 10, 1.0));
        }
        gameModel.setPlanets(planets);
    }

     /**
     * Tests starting a new level.
     * Checks that the current level, difficulty, zombied state, and game state are correctly initialized.
     */
    @Test
    void testStartLevel() {
        gameModel.startLevel();
        assertEquals(1, gameModel.getCurrentLevel());
        assertEquals(1.0, gameModel.getDifficultyFactor());
        assertFalse(gameModel.isZombied());
        assertEquals(1, gameState.getCurrentLevel());
    }

     /**
     * Tests advancing to the next level.
     * Verifies that level increments and the zombied state remains false.
     */
    @Test
    void testAdvanceLevel() {
        gameModel.startLevel();
        gameModel.advanceLevel();
        assertEquals(2, gameModel.getCurrentLevel());
        assertFalse(gameModel.isZombied());
    }

    @Test
    void testAdvanceToFinalLevel() {
        for (int i = 1; i <= 5; i++) {
            gameModel.advanceLevel();
        }
        assertTrue(gameModel.isZombied());
    }

    @Test
    void testResetLevel() {
        gameModel.startLevel();
        gameModel.resetLevel();
        assertEquals(1, gameModel.getCurrentLevel());
        assertFalse(gameModel.isZombied());
    }

    @Test
    void testResetGame() {
        gameModel.startLevel();
        gameModel.advanceLevel();
        gameState.updateScore(50);
        gameModel.resetGame();
        assertEquals(1, gameModel.getCurrentLevel());
        assertEquals(0, gameState.getScore());
        assertFalse(gameModel.isZombied());
    }

    @Test
    void testCheckFailure() {
        assertEquals("No trajectory.", gameModel.checkFailure(null));
        gameState.setZombied(true);
        assertEquals("Game over!", gameModel.checkFailure(gameModel.getLastTrajectory()));
    }

    @Test
    void testUpdateGameState() {
        gameModel.updateGameState(true);
        assertEquals(100, gameState.getScore());
        gameModel.updateGameState(false);
        assertEquals(90, gameState.getScore()); // penalty of -10
    }
}


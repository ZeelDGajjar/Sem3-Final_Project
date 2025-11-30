package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    private GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = new GameState();
    }

    // RESET TEST
    @Test
    public void testResetAll() {
        gameState.updateScore(50);
        gameState.addAttempts();
        gameState.addPlayTime(40);
        gameState.setCurrentLevel(5);
        gameState.setZombied(true);

        gameState.resetAll();

        assertEquals(0, gameState.getScore());
        assertEquals(0, gameState.getAttempts());
        assertEquals(0, gameState.getTotalPlayTimeSeconds());
        assertFalse(gameState.isZombied());
        assertEquals(1, gameState.getMaxLevelReached());
        assertEquals(1, gameState.getCurrentLevel());
    }

    // SCORE TESTS

    @Test
    public void testUpdateScorePositive() {
        gameState.updateScore(10);
        assertEquals(10, gameState.getScore());

        gameState.updateScore(5);
        assertEquals(15, gameState.getScore());
    }

    @Test
    public void testUpdateScoreNegativeButAboveZero() {
        gameState.updateScore(20);
        gameState.updateScore(-5);
        assertEquals(15, gameState.getScore());
    }

    @Test
    public void testUpdateScoreNegativeBelowZero() {
        gameState.updateScore(5);
        gameState.updateScore(-10);
        assertEquals(0, gameState.getScore());
    }

    @Test
    public void testUpdateScoreZeroDoesNothing() {
        gameState.updateScore(0);
        assertEquals(0, gameState.getScore());
    }

    // LEVEL TESTS
    @Test
    public void testSetCurrentLevelMinimumIsOne() {
        gameState.setCurrentLevel(-5);
        assertEquals(1, gameState.getCurrentLevel());
    }

    @Test
    public void testSetCurrentLevelUpdatesMaxLevel() {
        gameState.setCurrentLevel(3);
        assertEquals(3, gameState.getMaxLevelReached());

        gameState.setCurrentLevel(2);
        assertEquals(3, gameState.getMaxLevelReached()); // does not decrease
    }

    @Test
    public void testGetCurrentLevel() {
        gameState.setCurrentLevel(4);
        assertEquals(4, gameState.getCurrentLevel());
    }

    // ATTEMPTS TEST
    @Test
    public void testAddAttempts() {
       assertEquals(0, gameState.getAttempts(), "Initial attempts should be 0");

        gameState.addAttempts();
        gameState.addAttempts();
        assertEquals(2, gameState.getAttempts(), "Attempts should be 2 after adding twice");
    }

    // PLAY TIME TEST
    @Test
    public void testAddPlayTimePositive() {
        gameState.addPlayTime(30);
        assertEquals(30, gameState.getTotalPlayTimeSeconds());

        gameState.addPlayTime(20);
        assertEquals(50, gameState.getTotalPlayTimeSeconds());
    }

    @Test
    public void testAddPlayTimeNegativeDoesNothing() {
        gameState.addPlayTime(-10);
        assertEquals(0, gameState.getTotalPlayTimeSeconds());
    }

    // ZOMBIED / GAME OVER TEST
    @Test
    public void testZombiedState() {
        assertFalse(gameState.isZombied());
    assertEquals(0, gameState.getScore(), "Score should initially be 0");
    assertEquals(1, gameState.getCurrentLevel(), "Level should initially be 1");

    gameState.setZombied(true);
    assertTrue(gameState.isZombied());

    gameState.setZombied(false);
    assertFalse(gameState.isZombied());

    // Verify score and level remain unchanged
    assertEquals(0, gameState.getScore(), "Score should remain 0 after zombied toggled");
    assertEquals(1, gameState.getCurrentLevel(), "Level should remain 1 after zombied toggled");
    }

    @Test
    public void testIsGameOver() {
        assertFalse(gameState.isGameOver());
        gameState.setZombied(true);
        assertTrue(gameState.isGameOver());
    }
}

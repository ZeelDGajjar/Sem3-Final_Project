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

    // ----------- RESET TEST -----------
    @Test
    public void testResetAll() {
        gameState.updateScore(100);
        gameState.addAttempts();
        gameState.addPlayTime(50);
        gameState.setZombied(true);

        gameState.resetAll();

        assertEquals(0, gameState.getScore());
        assertEquals(0, gameState.getAttempts());
        assertEquals(0.0, gameState.getTotalPlayTimeSeconds());
        assertFalse(gameState.isZombied());
        assertEquals(1, gameState.getMaxLevelReached());
    }

    // ----------- SCORE TESTS ----------
    @Test
    public void testUpdateScorePositive() {
        gameState.updateScore(50);
        assertEquals(50, gameState.getScore());
    }

    @Test
    public void testUpdateScoreNegativeButNotBelowZero() {
        gameState.updateScore(20);
        gameState.updateScore(-10);
        assertEquals(10, gameState.getScore());
    }

    @Test
    public void testUpdateScoreCannotGoBelowZero() {
        gameState.updateScore(-100);
        assertEquals(0, gameState.getScore());
    }

    @Test
    public void testUpdateScoreZeroDoesNothing() {
        gameState.updateScore(0);
        assertEquals(0, gameState.getScore());
    }

    // ----------- LEVEL TESTS ----------
    @Test
    public void testSetCurrentLevelValid() {
        gameState.setCurrentLevel(5);
        assertEquals(5, gameState.getCurrentLevel());
        assertEquals(5, gameState.getMaxLevelReached());
    }

    @Test
    public void testSetCurrentLevelCannotBeBelowOne() {
        gameState.setCurrentLevel(-10);
        assertEquals(1, gameState.getCurrentLevel());
    }

    @Test
    public void testMaxLevelReachedUpdatesOnlyWhenHigher() {
        gameState.setCurrentLevel(3);
        gameState.setCurrentLevel(2);  // lower → should NOT update max
        assertEquals(3, gameState.getMaxLevelReached());
    }

    // ----------- ATTEMPTS TESTS ----------
    @Test
    public void testAddAttempts() {
        gameState.addAttempts();
        gameState.addAttempts();
        assertEquals(2, gameState.getAttempts());
    }

    // ----------- PLAY TIME TESTS ----------
    @Test
    public void testAddPlayTimePositive() {
        gameState.addPlayTime(30);
        assertEquals(30, gameState.getTotalPlayTimeSeconds());
    }

    @Test
    public void testAddPlayTimeNegativeDoesNothing() {
        gameState.addPlayTime(-50);
        assertEquals(0, gameState.getTotalPlayTimeSeconds());
    }

    // ----------- ZOMBIED TESTS ----------
    @Test
    public void testSetZombied() {
        gameState.setZombied(true);
        assertTrue(gameState.isZombied());
    }

    @Test
    public void testIsGameOverWhenZombied() {
        gameState.setZombied(true);
        assertTrue(gameState.isGameOver());
    }

    @Test
    public void testIsGameOverWhenNotZombied() {
        gameState.setZombied(false);
        assertFalse(gameState.isGameOver());
    }
}

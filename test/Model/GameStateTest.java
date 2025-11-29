package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    // ------------------------
    // RESET TEST
    // ------------------------
    @Test
    void testResetAll() {
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
        assertEquals(1, gameState.getCurrentLevel()); // because Math.max(1, level)
    }

    // ------------------------
    // SCORE TESTS
    // ------------------------
    @Test
    void testUpdateScorePositive() {
        gameState.updateScore(10);
        assertEquals(10, gameState.getScore());

        gameState.updateScore(5);
        assertEquals(15, gameState.getScore());
    }

    @Test
    void testUpdateScoreNegativeButAboveZero() {
        gameState.updateScore(20);
        gameState.updateScore(-5);
        assertEquals(15, gameState.getScore());
    }

    @Test
    void testUpdateScoreNegative

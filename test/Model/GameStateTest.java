package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 /**
 * Unit tests for GameState class, covering score, level, attempts,
 * playtime, and zombied (game over) behavior.
 * @author Vedika
 */
class GameStateTest {

    public GameState gameState;

     /** 
     * Initialize GameState before each test
     */
    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

     /** 
     * Test resetting all fields to initial state 
     */
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

    /**
    * Test adding positive score 
    */
    @Test
    void testUpdateScorePositive() {
        gameState.updateScore(10);
        assertEquals(10, gameState.getScore());

        gameState.updateScore(5);
        assertEquals(15, gameState.getScore());
    }

    /** 
    * Test negative score above zero 
    */
    @Test
    void testUpdateScoreNegativeButAboveZero() {
        gameState.updateScore(20);
        gameState.updateScore(-5);
        assertEquals(15, gameState.getScore());
    }

    /*
    * test negative score below zero
    */
    @Test
    void testUpdateScoreNegativeBelowZero() {
        gameState.updateScore(5);
        gameState.updateScore(-10);
        assertEquals(0, gameState.getScore()); // cannot go below 0
    }
 
    /**
     * Test zero score does nothing
     */
    @Test
    void testUpdateScoreZeroDoesNothing() {
        gameState.updateScore(0);
        assertEquals(0, gameState.getScore());
    }

    /**
    * current level cannot go below 1
    */
    @Test
    void testSetCurrentLevelMinimumIsOne() {
        gameState.setCurrentLevel(-5);
        assertEquals(1, gameState.getCurrentLevel());
    }
    
    /*
    * check if Max level updates correctly
    */
    @Test
    void testSetCurrentLevelUpdatesMaxLevel() {
        gameState.setCurrentLevel(3);
        assertEquals(3, gameState.getMaxLevelReached());

        gameState.setCurrentLevel(2); 
        assertEquals(3, gameState.getMaxLevelReached()); // does not decrease
    }
 
    /*
    * get current level test
    */
    @Test
    void testGetCurrentLevel() {
        gameState.setCurrentLevel(4);
        assertEquals(4, gameState.getCurrentLevel());
    }

    // test add attempts 
    @Test
    void testAddAttempts() {
        gameState.addAttempts();
        gameState.addAttempts();
        assertEquals(2, gameState.getAttempts());
    }

    /*
    * test adding positive play time
    */
    @Test
    void testAddPlayTimePositive() {
        gameState.addPlayTime(30);
        assertEquals(30, gameState.getTotalPlayTimeSeconds());

        gameState.addPlayTime(20);
        assertEquals(50, gameState.getTotalPlayTimeSeconds());
    }
     /**
     * Negative play time should be ignored
     **/
    @Test
    void testAddPlayTimeNegativeDoesNothing() {
        gameState.addPlayTime(-10);
        assertEquals(0, gameState.getTotalPlayTimeSeconds());
    }

    // ZOMBIED / GAME OVER TEST
    @Test
    void testZombiedState() {
        assertFalse(gameState.isZombied());

        gameState.setZombied(true);
        assertTrue(gameState.isZombied());

        gameState.setZombied(false);
        assertFalse(gameState.isZombied());
    }

    @Test
    void testIsGameOver() {
        assertFalse(gameState.isGameOver());
        gameState.setZombied(true);
        assertTrue(gameState.isGameOver());
    }
}



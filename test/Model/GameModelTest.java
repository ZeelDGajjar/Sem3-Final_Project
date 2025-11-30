package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    private GameModel gameModel;
    private GameState gameState;
    private Planet targetPlanet; 

   @BeforeEach
    void setUp() {
        gameState = new GameState();
        gameModel = new GameModel(gameState);

        // Set up planets for testing levels
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Earth", 100, 100, 10, 100));
        planets.add(new Planet("Mars", 200, 100, 10, 100));
        planets.add(new Planet("Venus", 300, 100, 10, 100));
        gameModel.setPlanets(planets);
        
         // Track the target planet for the first level
        targetPlanet = planets.get(0);
        gameModel.startLevel();
    }

     @Test
    void testStartLevel() {
        assertEquals(1, gameModel.getCurrentLevel());
        assertFalse(gameModel.isZombied());
        assertEquals(targetPlanet, gameModel.getTargetPlanet());
    }

    // ADVANCE LEVEL
    @Test
    void testAdvanceLevel() {
         gameModel.advanceLevel();
        assertEquals(2, gameModel.getCurrentLevel());
        assertEquals("Mars", gameModel.getTargetPlanet().getName());

    }
    
     @Test
    void testAdvanceToFinalLevelSetsZombied() {
        while (!gameModel.isFinalLevel()) {
            gameModel.advanceLevel();
        }
        // After advancing past final level
        gameModel.advanceLevel();
        assertTrue(gameModel.isZombied());
        assertTrue(gameState.isZombied());
    }

    // RESET LEVEL
    @Test
    void testResetLevel() {
        gameModel.resetLevel();
        assertNull(gameModel.getLastTrajectory());
        assertFalse(gameModel.isZombied());
        assertEquals(1, gameModel.getCurrentLevel());
    }

   
    // TIMER LOGIC
   @Test
    void testLevelTimeOut() throws InterruptedException {
        gameModel.setLevelTimeLimit(1); // 1 second for fast test
        Thread.sleep(1100);
        assertTrue(gameModel.isLevelTimeUp());
        assertTrue(gameState.isZombied());
    }

    // CHECK FAILURE
    @Test
    void testCheckFailure_NoTrajectory() {
    assertEquals("No trajectory.", gameModel.checkFailure(null));
    assertFalse(gameModel.isZombied(), "Game should not be zombied when trajectory is null");
    }
    
    // UPDATE GAME STATE
    @Test
    void testUpdateGameStateSuccess() {
        gameModel.startLevel();
        gameModel.updateGameState(true);
        assertEquals(100, gameState.getScore());
    }

    @Test
    void testUpdateGameStateFailure() {
        gameModel.startLevel();
        gameModel.updateGameState(false);
        assertEquals(0, gameState.getScore()); // penalty ex. -10 but score can't go below 0
    }
}

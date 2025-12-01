package Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameModelTest {

    private GameModel model;
    private GameState gameState;

    static class DummyPlanet extends Planet {
        public DummyPlanet(String name) {
            super(name, 0, 0, 10, 1.0, true);
        }
    }

    @Before
    public void setUp() {
        gameState = new GameState();
        model = new GameModel(gameState);

        // Add 5 dummy planets
        List<Planet> planets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            planets.add(new DummyPlanet("Planet" + i));
        }
        model.setPlanets(planets);
    }

    // ---------------------------
    // LEVEL LOGIC
    // ---------------------------
    @Test
    public void testStartLevel() {
        model.startLevel();
        assertEquals(1, model.getCurrentLevel());
        assertEquals(1.0, model.getDifficultyFactor(), 1e-10);
        assertFalse(model.isZombied());
        assertEquals(1, gameState.getCurrentLevel());
    }

    @Test
    public void testAdvanceLevel() {
        model.startLevel();
        model.advanceLevel();
        assertEquals(2, model.getCurrentLevel());
        assertFalse(model.isZombied());
    }

    @Test
    public void testAdvanceLevelFinalTriggersGameOver() {
        model.startLevel();
        for (int i = 1; i < 5; i++) {
            model.advanceLevel();
        }
        assertTrue(model.isZombied() || model.isFinalLevel());
    }

    @Test
    public void testResetLevel() {
        model.startLevel();
        model.setLastTrajectory(null);
        model.resetLevel();
        assertEquals(1, model.getCurrentLevel());
        assertFalse(model.isZombied());
    }

    @Test
    public void testIsFinalLevel() {
        assertFalse(model.isFinalLevel());
        for (int i = 1; i < 5; i++) model.advanceLevel();
        assertTrue(model.isFinalLevel());
    }

    // ---------------------------
    // TIMER LOGIC
    // ---------------------------
    @Test
    public void testTimerExpires() throws InterruptedException {
        model.setLevelTimeLimit(1); // 1 second
        model.startLevelTimer();
        Thread.sleep(1200);
        assertTrue(model.isLevelTimeUp());
        assertTrue(model.isZombied());
    }

    // ---------------------------
    // RESET GAME
    // ---------------------------
    @Test
    public void testResetGame() {
        model.startLevel();
        gameState.updateScore(50);
        gameState.addAttempts();
        model.advanceLevel();

        model.resetGame();
        assertEquals(1, model.getCurrentLevel());
        assertEquals(0, gameState.getScore());
        assertEquals(0, gameState.getAttempts());
        assertFalse(model.isZombied());
    }

    // ---------------------------
    // FAILURE CHECKS
    // ---------------------------
    @Test
    public void testCheckFailure_NoTrajectory() {
        assertEquals("No trajectory.", model.checkFailure(null));
    }

    @Test
    public void testCheckFailure_GameOver() {
        gameState.setZombied(true);
        assertEquals("Game over!", model.checkFailure(model.getLastTrajectory()));
    }

    // ---------------------------
    // UPDATE GAME STATE
    // ---------------------------
    @Test
    public void testUpdateGameStateSuccessAndFailure() {
        model.startLevel();
        model.updateGameState(true);
        assertEquals(100, gameState.getScore());

        model.updateGameState(false);
        assertEquals(90, gameState.getScore()); // penalty of -10
    }
}

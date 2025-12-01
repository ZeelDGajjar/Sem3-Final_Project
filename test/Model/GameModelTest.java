package Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class GameModelTest {

    private GameModel model;
    private GameState gameState;

    // ---------------------------
    // Dummy classes to satisfy dependencies
    // ---------------------------
    static class DummyPlanet extends Planet {
        public DummyPlanet() {
            super("Dummy", 0, 0, 10, 1.0, false);
        }
    }

    static class DummyTrajectory extends Trajectory {
        public DummyTrajectory() {
            super(new ArrayList<>());
        }
    }

    static class DummyProjectile extends Projectile {
        public DummyProjectile() {
            super(0, 0, 1.0, new Vector2(0, 0));
        }
    }

    // ---------------------------
    // Setup
    // ---------------------------
    @Before
    public void setUp() {
        gameState = new GameState();
        model = new GameModel(gameState);

        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            planets.add(new DummyPlanet());
        }
        model.setPlanets(planets);
    }

    // ---------------------------
    // START LEVEL
    // ---------------------------
    @Test
    public void testStartLevel() {
        model.startLevel();

        assertEquals(1, model.getCurrentLevel());
        assertEquals(1.0, model.getDifficultyFactor(), 1e-10);
        assertFalse(model.isZombied());
        assertEquals(1, gameState.getCurrentLevel());
    }

    // ---------------------------
    // ADVANCE LEVEL
    // ---------------------------
    @Test
    public void testAdvanceLevel() {
        model.startLevel();
        model.advanceLevel();

        assertEquals(2, model.getCurrentLevel());
        assertEquals(2, gameState.getCurrentLevel());
        assertFalse(model.isZombied());
    }

    @Test
    public void testAdvanceLevelFinalTriggersGameOver() {
        model.startLevel();

        for (int i = 1; i < 5; i++) {
            model.advanceLevel();
        }
        assertTrue(model.isFinalLevel());

        model.advanceLevel(); // after final
        assertTrue(model.isZombied());
        assertTrue(gameState.isZombied());
    }

    // ---------------------------
    // RESET LEVEL
    // ---------------------------
    @Test
    public void testResetLevel() {
        model.startLevel();
        model.setLastTrajectory(new DummyTrajectory());
        model.setProjectile(new DummyProjectile());

        model.resetLevel();

        assertEquals(1, model.getCurrentLevel());
        assertFalse(model.isZombied());
        assertNull(model.getLastTrajectory());
    }

    // ---------------------------
    // FINAL LEVEL CHECK
    // ---------------------------
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

        assertEquals(0, model.getRemainingLevelTime());
        assertTrue(model.isLevelTimeUp());
        assertTrue(gameState.isZombied());
    }

    // ---------------------------
    // RESET GAME
    // ---------------------------
    @Test
    public void testResetGame() {
        model.startLevel();
        gameState.updateScore(100);
        gameState.addAttempts();
        model.advanceLevel();

        model.resetGame();

        assertEquals(1, model.getCurrentLevel());
        assertEquals(1.0, model.getDifficultyFactor(), 1e-10);
        assertEquals(0, gameState.getScore());
        assertEquals(0, gameState.getAttempts());
        assertFalse(model.isZombied());
        assertFalse(gameState.isZombied());
    }

    // ---------------------------
    // CHECK FAILURE
    // ---------------------------
    @Test
    public void testCheckFailure_NoTrajectory() {
        assertEquals("No trajectory.", model.checkFailure(null));
    }

    @Test
    public void testCheckFailure_GameOver() {
        gameState.setZombied(true);
        assertEquals("Game over!", model.checkFailure(new DummyTrajectory()));
    }

    @Test
    public void testCheckFailure_WithFailureReason() {
        DummyTrajectory traj = new DummyTrajectory();
        traj.setFailureReason("Missed!");
        assertEquals("Missed!", model.checkFailure(traj));
    }

    @Test
    public void testCheckFailure_Success() {
        DummyTrajectory traj = new DummyTrajectory();
        traj.setFailureReason(null);
        assertEquals("Success!", model.checkFailure(traj));
    }

    // ---------------------------
    // UPDATE GAME STATE
    // ---------------------------
    @Test
    public void testUpdateGameStateSuccess() {
        model.startLevel();
        model.updateGameState(true);

        assertEquals(100, gameState.getScore());
    }

    @Test
    public void testUpdateGameStateFailure() {
        model.startLevel();
        model.updateGameState(false);

        assertEquals(0, gameState.getScore());
    }
}

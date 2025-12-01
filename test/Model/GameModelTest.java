package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    private GameModel model;
    private GameState gameState;

    // ---------- Dummy classes to satisfy dependencies ----------
    static class DummyPlanet extends Planet {
        public DummyPlanet() {
            super("Dummy", 0, 0, 10, 1.0);
        }
    }

    static class DummyTrajectory extends Trajectory {
        public DummyTrajectory() {
            super(new ArrayList<>()); // empty list of points
        }
    }

    static class DummyProjectile extends Projectile {
        public DummyProjectile() {
            super(0, 0, 1.0, new Vector2(0,0));
        }
    }

    @BeforeEach
    void setUp() {
        gameState = new GameState();
        model = new GameModel(gameState);

        // Add 5 dummy planets so GameModel can use them
        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < 5; i++) planets.add(new DummyPlanet());
        model.setPlanets(planets);
    }

    @Test
    void testStartLevel() {
        model.startLevel();

        assertEquals(1, model.getCurrentLevel());
        assertEquals(1.0, model.getDifficultyFactor());
        assertFalse(model.isZombied());
        assertEquals(1, gameState.getCurrentLevel());
    }

    @Test
    void testAdvanceLevelNonFinal() {
        model.startLevel();
        model.advanceLevel();

        assertEquals(2, model.getCurrentLevel());
        assertEquals(2, gameState.getCurrentLevel());
        assertFalse(model.isZombied());
    }

    @Test
    void testAdvanceLevelFinalTriggersGameOver() {
        model.startLevel();

        // Move to final level
        for (int i = 1; i < 5; i++) model.advanceLevel();

        assertTrue(model.isFinalLevel());

        model.advanceLevel(); // advancing AFTER final triggers zombied

        assertTrue(model.isZombied());
        assertTrue(gameState.isZombied());
    }

    @Test
    void testResetLevel() {
        model.startLevel();
        model.setLastTrajectory(new DummyTrajectory());
        model.setProjectile(new DummyProjectile());

        model.resetLevel();

        assertNull(model.getLastTrajectory());
        assertEquals(1, model.getCurrentLevel());
        assertFalse(model.isZombied());
    }

    @Test
    void testIsFinalLevel() {
        assertFalse(model.isFinalLevel());

        for (int i = 1; i < 5; i++) model.advanceLevel();
        assertTrue(model.isFinalLevel());
    }

    @Test
    void testLevelTimerCountsDown() throws InterruptedException {
        model.setLevelTimeLimit(1); // 1 second
        model.startLevelTimer();

        Thread.sleep(1200); // wait >1 sec

        long remaining = model.getRemainingLevelTime();

        assertEquals(0, remaining);
        assertTrue(model.isLevelTimeUp());
        assertTrue(model.isTimeUp());
        assertTrue(model.isZombied());
    }

    @Test
    void testResetGame() {
        model.startLevel();
        model.advanceLevel();
        gameState.updateScore(100);
        gameState.addAttempts();

        model.resetGame();

        assertEquals(1, model.getCurrentLevel());
        assertEquals(1.0, model.getDifficultyFactor());
        assertEquals(0, gameState.getScore());
        assertEquals(0, gameState.getAttempts());
        assertFalse(gameState.isZombied());
        assertFalse(model.isZombied());
    }

    @Test
    void testCheckFailure_NoTrajectory() {
        assertEquals("No trajectory.", model.checkFailure(null));
    }

    @Test
    void testCheckFailure_GameOver() {
        gameState.setZombied(true);
        assertEquals("Game over!", model.checkFailure(new DummyTrajectory()));
    }

    @Test
    void testCheckFailure_WithFailureReason() {
        DummyTrajectory traj = new DummyTrajectory();
        traj.setFailureReason("Missed!");
        assertEquals("Missed!", model.checkFailure(traj));
    }

    @Test
    void testCheckFailure_Success() {
        DummyTrajectory traj = new DummyTrajectory();
        traj.setFailureReason(null);
        assertEquals("Success!", model.checkFailure(traj));
    }

    @Test
    void testUpdateGameStateSuccess() {
        model.startLevel();
        model.updateGameState(true);
        assertEquals(100, gameState.getScore());
    }

    @Test
    void testUpdateGameStateFailure() {
        model.startLevel();
        model.updateGameState(false);
        assertEquals(0, gameState.getScore()); // penalty -10 but score can't go below 0
    }
}

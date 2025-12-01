package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class GameModelTest {

    private GameModel model;
    private GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = new GameState();
        model = new GameModel(gameState);

        // minimal setup: add some planets so levels can run
        List<Planet> planets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            planets.add(new Planet("Planet" + i, 0, 0, 10, 1.0));
        }
        model.setPlanets(planets);
    }

    @Test
    public void testStartLevel() {
        model.startLevel();

        assertEquals(1, model.getCurrentLevel(), "Level should start at 1");
        assertEquals(1, gameState.getCurrentLevel(), "GameState level should match");
        assertFalse(model.isZombied(), "Game should not be zombied at start");
    }

    @Test
    public void testAdvanceLevel() {
        model.startLevel();
        model.advanceLevel();

        assertEquals(2, model.getCurrentLevel(), "Level should advance to 2");
    }
}

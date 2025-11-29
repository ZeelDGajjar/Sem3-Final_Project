package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    private Planet planet;
    private Projectile projectile;

    @BeforeEach
    void setUp() {
        // Planet at (0,0) radius 10, mass 100
        planet = new Planet("Earth", 0, 0, 10, 100, true);
        // Projectile at origin by default
        projectile = new Projectile(0, 0, 1.0, new Vector2(0,0));
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Earth", planet.getName());
        assertEquals(0, planet.getX());
        assertEquals(0, planet.getY());
        assertEquals(10, planet.getRadius());
        assertEquals(100, planet.getMass());
        assertTrue(planet.isTarget());
    }

    @Test
    void testCheckHit_Hit() {
        // Projectile at center, should hit
        assertTrue(planet.checkHit(projectile));
    }

    @Test
    void testCheckHit_Miss() {
        // Projectile far away
        projectile.setPosition(new Vector2(100, 100));
        assertFalse(planet.checkHit(projectile));
    }

    @Test
    void testIncreaseRadiusFactor() {
        planet.increaseRadiusFactor(2);
        assertEquals(20, planet.getRadius());
    }

    @Test
    void testDecreaseRadiusFactor() {
        planet.decreaseRadiusFactor(2);
        assertEquals(5, planet.getRadius());
    }

    @Test
    void testGenerateRandomPosition() {
        planet.generateRandomPosition(2);
        // Should be between 200 and 600 (min=100*2, max=300*2)
        assertTrue(planet.getX() >= 200 && planet.getX() <= 600);
        assertTrue(planet.getY() >= 200 && planet.getY() <= 600);
    }

    @Test
    void testCalculateGravityAt() {
        Vector2 point = new Vector2(3, 4); // distance = 5
        double expectedGravity = 100 / (5 * 5); // mass / distance^2
        assertEquals(expectedGravity, planet.calculateGravityAt(point), 1e-10);
    }

    @Test
    void testCalculateGravityAtZeroDistance() {
        Vector2 point = new Vector2(0,0); // same as planet position
        assertEquals(0, planet.calculateGravityAt(point));
    }

    @Test
    void testToString() {
        String str = planet.toString();
        assertTrue(str.contains("Earth"));
        assertTrue(str.contains("radius=10"));
        assertTrue(str.contains("target=true"));
    }
}

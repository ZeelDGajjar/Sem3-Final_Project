package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    private Planet planet;
    private Projectile projectile;


    @Test
    void testConstructorAndGetters() {
        Planet planet = new Planet("Earth",0,0,10,100);
        assertEquals(0, planet.getX());
        assertEquals(0, planet.getY());
        assertEquals(10, planet.getRadius());
        assertEquals(100, planet.getMass());
        assertTrue(planet.isTarget()); //default is false since it's not set in constructor
    }

    @Test
    void testCheckHit_Hit() {
       Planet planet = new Planet("Earth", 0, 0, 10, 100);
       Projectile projectile = new Projectile(0, 0, 1.0, new Vector2(10, 0)); // exactly on edge

        assertTrue(planet.checkHit(projectile), "Projectile should hit at the edge of the radius");
    }

    @Test
    void testCheckHit_Miss() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);
        Projectile projectile = new Projectile(0, 0, 1.0, new Vector2(100, 100)); // far away
        assertFalse(planet.checkHit(projectile));
       }

    @Test
    void testIncreaseRadiusFactor() {
        
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        planet.increaseRadiusFactor(2);
        assertEquals(20, planet.getRadius(), "Radius should double");

        // Changed: decrease back to original and check
        planet.decreaseRadiusFactor(2);
        assertEquals(10, planet.getRadius(), "Radius should return to original after decrease");
    }

    @Test
    void testDecreaseRadiusFactor() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        planet.decreaseRadiusFactor(2);
        assertEquals(5, planet.getRadius());
    }

    @Test
    void testGenerateRandomPosition() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        planet.generateRandomPosition(2);
        // Should be between 200 and 600 (min is 100*2, max is 300*2)
        assertTrue(planet.getX() >= 200 && planet.getX() <= 600);
        assertTrue(planet.getY() >= 200 && planet.getY() <= 600);
    }

    @Test
    void testCalculateGravityAt() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        Vector2 point = new Vector2(3, 4); // distance = 5
        double expectedGravity = 100 / (5 * 5); // mass / distance^2
        assertEquals(expectedGravity, planet.calculateGravityAt(point), 1e-10);
    }

    @Test
    void testCalculateGravityAtZeroDistance() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        Vector2 point = new Vector2(0,0); // same as planet position
        assertEquals(0, planet.calculateGravityAt(point));
    }

    @Test
    void testToString() {
        Planet planet = new Planet("Earth", 0, 0, 10, 100);

        String str = planet.toString();
        assertTrue(str.contains("Earth"));
        assertTrue(str.contains("radius=10"));
        assertTrue(str.contains("target=true"));
    }
}

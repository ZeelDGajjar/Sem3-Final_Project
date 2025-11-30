package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionUtilTest {

    private Planet planet;
    private Projectile projectile;

    @BeforeEach
    void setUp() {
        // Planet at (50,50) radius 10
        planet = new Planet("Earth", 50, 50, 10, 100);
        // Projectile initially at (50,50)
        projectile = new Projectile(0, 0, 1.0, new Vector2(50, 50));
    }

    @Test
    void testCheckCollision_Hit() {
        assertTrue(CollisionUtil.checkCollision(projectile, planet), "Projectile should hit planet");
    }
    
    @Test
    void testCheckCollision_Miss() {
        projectile.setPosition(new Vector2(-20, -20));
        assertFalse(CollisionUtil.checkCollision(projectile, planet), "Projectile should miss planet diagonally");
    }

    @Test
    void testCheckAnyCollision_Hit() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Mars", 0, 0, 5, 50));
        planets.add(planet); // second planet will be hit

        Planet hit = CollisionUtil.checkAnyCollsion(projectile, planets);
        assertNotNull(hit, "Should detect a hit");
        assertEquals(planet, hit, "Hit planet should be Earth");
    }

    @Test
    void testCheckAnyCollision_None() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Mars", 0, 0, 5, 50));
        planets.add(new Planet("Venus", 20, 20, 5, 50));
        planets.add(new Planet("Jupiter", 80, 80, 10, 200));

        // Place projectile far from all planets
        projectile.setPosition(new Vector2(200, 200));

        Planet hit = CollisionUtil.checkAnyCollsion(projectile, planets);
        assertNull(hit, "Projectile should not hit any planet");
    }

    @Test
    void testIsOutOfBounds_Inside() {
        projectile.setPosition(new Vector2(50, 50));
        assertFalse(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile inside bounds");
    }

    @Test
    void testIsOutOfBounds_Outside() {
        projectile.setPosition(new Vector2(-10, 50));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds left");

        projectile.setPosition(new Vector2(50, -5));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds top");

        projectile.setPosition(new Vector2(150, 50));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds right");

        projectile.setPosition(new Vector2(50, 150));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds bottom");
    }
}

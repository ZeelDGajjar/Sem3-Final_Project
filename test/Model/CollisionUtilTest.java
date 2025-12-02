package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CollisionUtil class.
 * Tests collision detection between projectiles and planets, as well as boundary checks.
 */
public class CollisionUtilTest {

    private Planet planet;
    private Projectile projectile;

     /**
     * Set up test objects before each test.
     */
    @BeforeEach
    void setUp() {
        // Planet at (50,50) radius 10 and mass 100
        planet = new Planet("Earth", 50, 50, 10, 100);
        
        // Projectile initially at (50,50), same location as the planet
        projectile = new Projectile(0, 0, 1.0, new Vector2(50, 50));
    }

    /**
    * Test collision detection when projectile hits the planet.
    */
    @Test
    void testCheckCollision_Hit() {
        assertTrue(CollisionUtil.checkCollision(projectile, planet), "Projectile should hit planet");
    }

     /**
     * Test collision detection when projectile misses the planet.
     */
    @Test
    void testCheckCollision_Miss() {
        //move projectile away from the planet 
        projectile.setPosition(new Vector2(0, 0));
        assertFalse(CollisionUtil.checkCollision(projectile, planet), "Projectile should miss planet");
    }

    /**
     * Test collision detection against a list of planets when there is a hit.
     */
    @Test
    void testCheckAnyCollision_Hit() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Mars", 0, 0, 5, 50));
        planets.add(planet); // second planet will be hit

        Planet hit = CollisionUtil.checkAnyCollsion(projectile, planets);
        assertNotNull(hit, "Should detect a hit");
        assertEquals(planet, hit, "Hit planet should be Earth");
    }

    /**
     * Test collision detection against a list of planets when there is no hit.
    */
    @Test
    void testCheckAnyCollision_None() {
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Mars", 0, 0, 5, 50));

        //move projectile away from planets
        projectile.setPosition(new Vector2(100, 100));
        Planet hit = CollisionUtil.checkAnyCollsion(projectile, planets);
        assertNull(hit, "No planet should be hit");
    }

     /**
     * Test isOutOfBounds method when projectile is inside the bounds.
     */
    @Test
    void testIsOutOfBounds_Inside() {
        projectile.setPosition(new Vector2(50, 50));
        assertFalse(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile inside bounds");
    }

    /**
     * Test isOutOfBounds method when projectile is outside the bounds.
     */
    @Test
    void testIsOutOfBounds_Outside() {
        
        //left
        projectile.setPosition(new Vector2(-10, 50));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds left");

        //top
        projectile.setPosition(new Vector2(50, -5));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds top");

        //right 
        projectile.setPosition(new Vector2(150, 50));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds right");

        //bottom
        projectile.setPosition(new Vector2(50, 150));
        assertTrue(CollisionUtil.isOutOfBounds(projectile, 100, 100), "Projectile outside bounds bottom");
    }
}

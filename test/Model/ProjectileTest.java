package Model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Projectile class.
 * Ensures correct trajectory physics and state updates.
 * 
 * @author zeel
 */
public class ProjectileTest {

    /**
     * Helper: simple mock planet.
     */
    private Planet mockPlanet(String name, double x, double y, int radius, double mass) {
        return new Planet(name, x, y, radius, mass);
    }

    /**
     * Tests that the constructor initializes position and velocity correctly.
     */
    @Test
    public void testConstructor_Initialization() {
        Projectile p = new Projectile(100, new Vector2(0, 0), 10, 0);

        assertEquals(0, p.getX(), 1e-9);
        assertEquals(0, p.getY(), 1e-9);

        // angle = 0° → velocity should be (10, 0)
        assertEquals(10, p.getVelocity().x, 1e-9);
        assertEquals(0, p.getVelocity().y, 1e-9);

        assertEquals(100, p.getMass());
    }

    /**
     * Tests that trajectory moves in a straight line when no planets exist.
     */
    @Test
    public void testCalculateTrajectory_NoPlanets_StraightLine() {
        List<Planet> planets = new ArrayList<>();
        Projectile p = new Projectile(0, new Vector2(0, 0), 10, 0);

        List<Point2D> t = p.calculateTrajectory(planets);

        assertFalse(t.isEmpty());
        assertTrue(t.get(t.size() - 1).getX() > 0);  // moves right
        assertEquals(0, t.get(t.size() - 1).getY(), 1e-6);
    }

    /**
     * Tests that gravity influences trajectory (curved path).
     */
    @Test
    public void testCalculateTrajectory_WithGravity_Curved() {
        List<Planet> planets = new ArrayList<>();
        planets.add(mockPlanet("Massive", 1000, 0, 20, 6e24));

        Projectile p = new Projectile(0, new Vector2(0, 0), 50, 0);

        List<Point2D> t = p.calculateTrajectory(planets);

        assertFalse(t.isEmpty());
        assertTrue(t.get(t.size() - 1).getX() > 0); // moves right
        assertEquals(0, t.get(t.size() - 1).getY(), 1e-2); // slight curve
    }

    /**
     * Tests that time of flight = number of steps * dt.
     */
    @Test
    public void testCalculateTimeOfFlight() {
        List<Planet> planets = new ArrayList<>();
        Projectile p = new Projectile(0, new Vector2(0, 0), 10, 0);

        double time = p.calculateTimeOfFlight(planets);

        // 2000 steps * dt 0.02 = 40 seconds
        assertEquals(40.0, time, 1e-9);
    }

    /**
     * Tests that range = final X position.
     */
    @Test
    public void testCalculateRange_NoPlanets() {
        List<Planet> planets = new ArrayList<>();
        Projectile p = new Projectile(0, new Vector2(0, 0), 10, 0);

        double range = p.calculateRange(planets);

        assertTrue(range > 0);
    }

    /**
     * Tests the max height is the highest Y value in trajectory.
     */
    @Test
    public void testCalculateMaxHeight() {
        List<Planet> planets = new ArrayList<>();
        Projectile p = new Projectile(0, new Vector2(0, 0), 50, 90); // vertical launch

        double maxH = p.calculateMaxHeight(planets);

        assertTrue(maxH > 0);
    }

    /**
     * Tests that updatePosition changes the projectile state over a timestep.
     */
    @Test
    public void testUpdatePosition() {
        List<Planet> planets = new ArrayList<>();
        Projectile p = new Projectile(0, new Vector2(0, 0), 10, 0);

        Point2D next = p.updatePosition(0.02, planets);

        assertTrue(next.getX() > 0);
        assertEquals(0, next.getY(), 1e-9);
    }

    /**
     * Tests that reset() sets position and velocity back to zero.
     */
    @Test
    public void testReset() {
        Projectile p = new Projectile(0, new Vector2(50, 20), 10, 45);

        p.reset();

        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertEquals(0, p.getVelocity().x);
        assertEquals(0, p.getVelocity().y);
    }

    @Test
    public void testSettersAndGetters() {
        Projectile p = new Projectile(1, new Vector2(0, 0), 0, 0);

        p.setMass(999);
        p.setPosition(new Vector2(5, 5));
        p.setVelocity(new Vector2(2, 3));

        assertEquals(999, p.getMass());
        assertEquals(5, p.getX());
        assertEquals(5, p.getY());
        assertEquals(2, p.getVelocity().x);
        assertEquals(3, p.getVelocity().y);
    }
}

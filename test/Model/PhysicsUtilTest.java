package Model;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PhysicsUtil class.
 * Validates gravitational acceleration and trajectory simulation.
 * 
 * @author zeel
 */
public class PhysicsUtilTest {

    /**
     * Helper: creates a simple mock planet for testing.
     */
    private Planet mockPlanet(String name, double x, double y, int radius, double mass) {
        return new Planet(name, x, y, radius, mass);
    }

    /**
     * Tests that computeGravity returns zero acceleration
     * when no planets are present.
     */
    @Test
    public void testComputeGravity_NoPlanets() {
        Vector2 pos = new Vector2(0, 0);
        List<Planet> planets = new ArrayList<>();

        Vector2 accel = PhysicsUtil.computeGravity(1000, pos, planets);

        assertEquals(0, accel.x);
        assertEquals(0, accel.y);
    }

    /**
     * Tests that computeGravity produces a positive X acceleration
     * when a planet is located to the right of the mass.
     */
    @Test
    public void testComputeGravity_SinglePlanetPull() {
        List<Planet> planets = new ArrayList<>();
        planets.add(mockPlanet("TestPlanet", 10, 0, 10, 5e20));

        Vector2 position = new Vector2(0, 0);
        double mass = 1000;

        Vector2 accel = PhysicsUtil.computeGravity(mass, position, planets);

        assertTrue(accel.x > 0);
        assertEquals(0, accel.y, 1e-12);
    }

    /**
     * Tests that computeGravity ignores any planet located exactly
     * at the same coordinates as the object (zero distance).
     */
    @Test
    public void testComputeGravity_ZeroDistanceIgnored() {
        List<Planet> planets = new ArrayList<>();
        planets.add(mockPlanet("SameSpot", 0, 0, 10, 5e20));

        Vector2 accel = PhysicsUtil.computeGravity(1000, new Vector2(0, 0), planets);

        assertEquals(0, accel.x);
        assertEquals(0, accel.y);
    }

    /**
     * Tests that trajectory with no planets results in straight-line motion
     * consistent with basic kinematics.
     */
    @Test
    public void testTrajectory_NoGravity_StraightLineMotion() {
        List<Planet> planets = new ArrayList<>(); // no gravity

        Vector2 start = new Vector2(0, 0);
        double speed = 10;
        double angle = 0;
        double t = 1.0;
        double mass = 100;

        Point2D result = PhysicsUtil.trajectory(speed, angle, t, planets, start, mass);

        assertEquals(10, result.getX(), 0.1);
        assertEquals(0, result.getY(), 0.1);
    }

    /**
     * Tests that trajectory is affected by gravity, causing the path
     * to deviate from a perfect straight line.
     */
    @Test
    public void testTrajectory_WithGravity_ChangesPath() {
        List<Planet> planets = new ArrayList<>();
        planets.add(mockPlanet("GravityCenter", 1000, 0, 20, 6e24));

        Vector2 start = new Vector2(0, 0);
        double speed = 100;
        double angle = 0;
        double t = 2.0;
        double mass = 2000;

        Point2D result = PhysicsUtil.trajectory(speed, angle, t, planets, start, mass);

        assertTrue(result.getX() > 0);
        assertEquals(0, result.getY(), 1e-3);
    }
}

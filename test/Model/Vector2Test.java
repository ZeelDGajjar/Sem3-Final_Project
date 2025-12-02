package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Vector2
 * @author zeelg
 */
public class Vector2Test {

    /**
     * Test the add() method
     */
    @Test
    public void testAdd() {
        Vector2 v1 = new Vector2(2, 3);
        Vector2 v2 = new Vector2(4, 5);
        Vector2 result = v1.add(v2);

        assertEquals(6, result.x, 1e-12, "X component should be sum of both vectors");
        assertEquals(8, result.y, 1e-12, "Y component should be sum of both vectors");
    }

    /**
     * Test the subtract() method
     */
    @Test
    public void testSubtract() {
        Vector2 v1 = new Vector2(5, 7);
        Vector2 v2 = new Vector2(2, 3);
        Vector2 result = v1.subtract(v2);

        assertEquals(3, result.x, 1e-12, "X component should be difference");
        assertEquals(4, result.y, 1e-12, "Y component should be difference");
    }

    /**
     * Test the scale() method
     */
    @Test
    public void testScale() {
        Vector2 v = new Vector2(3, 4);
        Vector2 result = v.scale(2);

        assertEquals(6, result.x, 1e-12, "X component should be scaled by factor");
        assertEquals(8, result.y, 1e-12, "Y component should be scaled by factor");
    }

    /**
     * Test the magnitude() method
     */
    @Test
    public void testMagnitude() {
        Vector2 v = new Vector2(3, 4);
        double mag = v.magnitude();

        assertEquals(5, mag, 1e-12, "Magnitude should be sqrt(x^2 + y^2)");
    }

    /**
     * Test the normalize() method
     */
    @Test
    public void testNormalize() {
        Vector2 v = new Vector2(3, 4);
        Vector2 norm = v.normalize();

        double expectedX = 3.0 / 5.0;
        double expectedY = 4.0 / 5.0;

        assertEquals(expectedX, norm.x, 1e-12, "Normalized X component");
        assertEquals(expectedY, norm.y, 1e-12, "Normalized Y component");
        assertEquals(1.0, norm.magnitude(), 1e-12, "Magnitude of normalized vector should be 1");
    }

    /**
     * Test normalization of zero vector
     */
    @Test
    public void testNormalizeZeroVector() {
        Vector2 zero = new Vector2(0, 0);
        Vector2 norm = zero.normalize();

        assertEquals(0, norm.x, 1e-12, "Normalized zero vector X should be 0");
        assertEquals(0, norm.y, 1e-12, "Normalized zero vector Y should be 0");
        assertEquals(0, norm.magnitude(), 1e-12, "Magnitude of normalized zero vector should be 0");
    }
}

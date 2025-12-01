package Model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Trajectory
 * Tests adding points, success flag, and failure reason
 * 
 * @author zeelg
 */
public class TrajectoryTest {

    /**
     * Tests default constructor initializes empty path
     */
    @Test
    public void testDefaultConstructor() {
        Trajectory traj = new Trajectory();
        assertNotNull(traj.getPath(), "Path should not be null");
        assertEquals(0, traj.getPath().size(), "Path should be empty");
        assertFalse(traj.getSuccess(), "Success should be false by default");
        assertNull(traj.getFailureReason(), "Failure reason should be null by default");
    }

    /**
     * Tests constructor with initial path
     */
    @Test
    public void testConstructorWithPath() {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D(1, 2));
        Trajectory traj = new Trajectory(points);

        assertEquals(1, traj.getPath().size(), "Path should contain one point");
        assertEquals(points.get(0), traj.getPath().get(0), "Point should match initial point");
    }

    /**
     * Tests adding points to the trajectory
     */
    @Test
    public void testAddPoint() {
        Trajectory traj = new Trajectory();
        Point2D p1 = new Point2D(3, 4);
        traj.addPoint(p1);

        assertEquals(1, traj.getPath().size(), "Path should contain one point");
        assertEquals(p1, traj.getPath().get(0), "Point added should match the stored point");
    }

    /**
     * Tests setting the success flag
     */
    @Test
    public void testSetSuccess() {
        Trajectory traj = new Trajectory();
        traj.setSuccess();
        assertTrue(traj.getSuccess(), "Success flag should be true after setSuccess()");
    }

    /**
     * Tests setting failure reason
     */
    @Test
    public void testSetFailureReason() {
        Trajectory traj = new Trajectory();
        String reason = "Collision detected";
        traj.setFailureReason(reason);

        assertEquals(reason, traj.getFailureReason(), "Failure reason should match the string set");
    }
}


package Model;

import java.util.List;
import javafx.geometry.Point2D;

/**
 * Trajectory class that handles the trajectory
 * @author zeelg
 */
public class Trajectory {
    private List<Point2D> path;
    private boolean success;
    private String failureReason;
    
    public Trajectory(){}
    
    public Trajectory(List<Point2D> path) {
        this.path = path;
    }
    
    /**
     * Adds points to the path list
     * @param p The given point to add
     */
    public void addPoint(Point2D p) {
        path.add(p);
    }
    
    // Getters and Setters
    public boolean getSuccess() {
        return success;
    }
    
    public String getFailureReason() {
        return failureReason;
    }
    
    public void setSuccess() {
        success = true;
    }
    
    public void setFailureReason(String reason) {
        failureReason = reason;
    }
}
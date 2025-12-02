package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import Controller.GameViewController;

/**
 * Trajectory class that handles the trajectory
 * @author zeelg
 */
public class Trajectory {
    // store path points in UI/model coordinate space (Point2D)
    private List<Point2D> path;
    private boolean success;
    private String failureReason;
    
    public Trajectory(){
        this.path = new ArrayList<>();
        this.success = false;
        this.failureReason = null;
    }
    
    public Trajectory(List<Point2D> path) {
        this.path = (path != null) ? path : new ArrayList<>();
        this.success = false;
        this.failureReason = null;
    }
    
    /**
     * Adds points to the path list
     * @param p The given point to add
     */
    public void addPoint(Point2D p) {
        if (this.path == null) this.path = new ArrayList<>();
        path.add(p);
    }
    
    // Public getter for the path so views/controllers can read simulated points
    public List<Point2D> getPath() {
        return path;
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

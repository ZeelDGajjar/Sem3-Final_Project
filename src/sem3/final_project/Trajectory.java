/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import java.util.List;
import javafx.scene.effect.Light.Point;

/**
 *
 * @author zeelg
 */
public class Trajectory {
    private List<Point> path;
    private boolean success;
    private String failureReason;
    
    public Trajectory(List<Point> path, boolean success, String failureReason) {
        this.path = path;
        this.success = success;
        this.failureReason = failureReason;
    }
    
    /**
     * Adds points to the path list
     * 
     * @param p The given point to add
     */
    public void addPoint(Point p) {
        path.add(p);
    }
    
    /**
     * Getters and setters
     * 
     * @return 
     */
    public boolean getSuccess() {
        return success;
    }
    
    public String failureReason() {
        return failureReason;
    }
    
    public void setSuccess() {
        success = true;
    }
    
    public void setfailureReason(String reason) {
        failureReason = reason;
    }
}

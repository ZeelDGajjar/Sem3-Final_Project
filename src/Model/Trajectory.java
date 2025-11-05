/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.effect.Light.Point;

/**
 *
 * @author ZeelG 2474008
 */
public class Trajectory {
    private Map<Point, Point> path;
    private boolean success;;
    private String failureReason;
    final private List<Character> points = new ArrayList<>();
    
    public Trajectory() {
        points.add(1, 'X');
        points.add(2, 'Y');
        points.add(3, 'Z');
    }
    
    public Trajectory(Map<Point, Point> path) {
        this.path = path;
        points.add(1, 'X');
        points.add(2, 'Y');
        points.add(3, 'Z');
    }

    /**
     * Adds points to the path
     * @param point 
     */
    public void addPoint(Map<Point,Point> point) {
    }

    public Map<Point, Point> getPath() {
        return path;
    }

    public boolean isSuccess() {
        return success;
    }

    // Might remove it, depends on how other classes use it
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailureReason() {
        return failureReason;
    }

    // Might remove it, depends on how other classes use it
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public List<Character> getPoints() {
        return points;
    }
}

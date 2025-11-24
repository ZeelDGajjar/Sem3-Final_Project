/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.*;
import java.util.List;
import java.util.Vector;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import Controller.GameViewController;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * The projectile class that calculates the projectile motion of the rocket
 * @author zeelg
 */
public class Projectile {
    private double speed;
    private double angle;
    private double mass;             
    private Point2D position;                // Need to take into account any changing velocity
    private double xVelocity;
    private double yVelocity;
    private Planet planet;
    
    public Projectile(double speed, double angle, Planet planet) {
        this.speed = speed;
        this.angle = Math.toRadians(angle);
        this.planet = planet;
        xVelocity = speed * cos(angle);
        yVelocity = speed * sin(angle);
    }
    
    public Projectile(double speed, double angle, double mass, Point2D position, Planet planet) {
        this.speed = speed;
        this.angle = angle;
        this.mass = mass;
        this.position = position;
        this.planet = planet;
        this.xVelocity = speed * cos(angle);
        this.yVelocity = speed * sin(angle);
    }
    
    public List<Point2D> calculateTrajectory(Planet planet) {
    }
    
    public double calculateRange() {
        
        return (Math.pow(speed, 2) * sin(angle) * 2 ) / (planet.gravity); 
    }
    
    /**
     * Calculates the time of a flight based on the target planet
     * @param planet the given target planet
     * @return a double value that shows the seconds of the flight
     */
    public double calculateTimeOfFlight(Planet planet) {
        return ((2 * speed * sin(angle)) / (planet.gravity));
    }
    
    /**
     * Calculates the max Height based on a given planet
     * @param planet the given planet 
     * @return a double value of the max height
     */
    public double calculateMaxHeight(Planet planet) {
        return Math.pow(speed * Math.sin(angle), 2) / (2 * planet.gravity);
    }
    
    /**
     * Updates position based on given position --might not be correct
     * @param point2D the given point2D
     */
    public void updatePosition(Point2D position) {
        this.position = position;
    }
    
    /**
     * Resets the original position
     */
    public void reset() {
    }
    
    // Setters and Getters
    public Point2D getPosition() {
        return position;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public double getMass() {
        return mass;
    }
    
    public void setPosition(Point2D position) {
        this.position = position;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    public void setMass(double mass) {
        this.mass = mass;
    }
}

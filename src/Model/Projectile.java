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

/**
 *
 * @author zeelg
 */
public class Projectile {
    private double speed;
    private double angle;
    private double mass;
    private Vector position;
    private Vector velocity;
    
    public Projectile(int speed, int angle, int par2, int par3) {
        this.speed = speed;
        this.angle = angle;
    }
    
    public Projectile(double speed, double angle, double mass, Vector position, Vector velocity) {
        this.speed = speed;
        this.angle = angle;
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
    }
    
//    public List<Point2D> calculateTrajectory(List<Planet> planets) {
//        return;
//    }
    
    public double calculateRange(List<Planet> planets) {
        return 0; 
    }
    
    public double calculateTimeOfFlight(List<Planet> planets) {
        return 0;
    }
    
    public double clculateMaxHeight(List<Planet> planets) {
        return 0;
    }
    
    public Point2D updatePosition(double time, List<Planet> planets) {
        return new Point2D(4,5);
    }
    
    public void reset() {
    }
    
    // Setters and Getters
    public Vector getPosition() {
        return position;
    }
    
    public Vector getVelocity() {
        return velocity;
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
    
    public void setPosition(Vector position) {
        this.position = position;
    }
    
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
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

    public double getX() {
        return (double) position.get(0);   
    }

    double getY() {
        return (double) position.get(1);
    }
}

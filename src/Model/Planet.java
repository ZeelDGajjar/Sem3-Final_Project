/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Random;
import java.util.Vector;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;

/**
 * Represents a planet in the MODEL layer of the game
 * A planet stores only data (position, radius, name etc)
 * and provides collision/ hit detection logic for the physics after
 * @author Vedika
 */
public class Planet {

    public Planet(Planet planet, double radiu, Point2D position) {
    }
    double gravity;

    private String name; 
    private Point2D position; 
    private double radius; 
    private boolean isTarget; 
    private Point2D coordinates; 
    private double distance; 
    private double mass; 
    
    /**
     * Constructor that creates a new planet 
     * @param name name of the planet 
     * @param x x-coordinate
     * @param y y -coordinate
     * @param radius collision radius
     * @param mass mass used when gravity is applied
     * @param isTarget whether this planet is the target for the current level
     */
    public Planet(String name, double x, double y, int radius, double mass, boolean isTarget) {
        this.name = name;
        this.position = new Point2D(x,y);
        this.radius = radius;
        this.mass = mass;
        this.isTarget = isTarget;
    }
    
    /**
     * Checks if a projectile collides with this planet.
     * @param projectile the projectile to test
     * @return true if the projectile hits the planet
     */
    public boolean checkHit(Projectile projectile) {
        Point2D projPos = projectile.getPosition();
        double dx = projPos.getX() - getX();
        double dy = projPos.getY() - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= radius;
    }
//    /**
//     * Distance from projectile to this planet's center
//     * @param projectile the projectile to measure 
//     * @return distance in the units of the game
//     */
//    public double getDistanceFromProjectile(Projectile projectile) {
//        return projectile.getPosition().distance(position);
//    }
    
    /**
     * Randomize planet position based on level of difficulty
     * Higher levels place planets more far 
     * @param level 
     */
    public void generateRandomPosition(int level) {
        Random rand = new Random();
        double min = 100 * level;
        double max = 300 * level;
        double x = min + rand.nextDouble() * (max - min);
        double y = min + rand.nextDouble() * (max - min);
        this.position = new Point2D(x,y);
    }
    
    /**
     * increase radius to make the planet easier to hit 
     * @param factor multiplier for size increase
     */
    public void increaseRadiusFactor(double factor) {
        this.radius = (int) (this.radius * factor); 
    }
    
    /**
     * Decrease radius to make the planet harder to hit 
     * @param factor factor divisor for size reduction 
     */
    public void decreaseRadiusFactor(double factor) {
        this.radius = (int) (this.radius / factor);
    }
    
    /**
     * Calculate gravitational influence on a projectile
     * @param Projectilepos
     * @return gravity strength value
     */
    public double calculateGravityAt(Point2D point) {
        double dx = getX() - point.getX();
        double dy = getY() - point.getY();
        double distance = Math.sqrt(dx*dx + dy*dy);
        if (distance == 0) return 0;
        return mass / (distance * distance);
    }
    

    
    @Override
    public String toString() {
        return "Planet{name=" + name +
               ", x=" + getX() +
               ", y=" + getY() +
               ", radius=" + radius +
               ", target=" + isTarget + "}";
    }
    
    
   public double getX() {
    return position.getX(); 
   }
   
    public double getY() {
     return position.getY(); 
    }
    
    public Point2D getCoordinates() {
        return position; 
    }
    
    public double getMass() {
        return mass; 
    }
    
    public boolean isTarget() {
        return isTarget; 
    }
    
    public String getName() {
        return name; 
    }
    
    
    public double getRadius() {
        return radius; 

    }
}

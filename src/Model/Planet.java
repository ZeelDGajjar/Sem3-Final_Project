/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Random;
import java.util.Vector;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import Model.Vector2;

/**
 * Represents a planet in the MODEL layer of the game
 * A planet stores only data (position, radius, name etc)
 * and provides collision/ hit detection logic for the physics after
 * @author Vedika
 */
public class Planet {

    private String name; 
    private Vector2 position;  
    private double radius; 
    private boolean isTarget; 
    private double mass; 
    
    /**
     * Constructor using Vector2 for position
     */
    public Planet(String name, double x, double y, int radius, double mass, boolean isTarget) {
        this.name = name;
        this.position = new Vector2(x, y);  // store physics-friendly vector
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
        Vector2 projPos = projectile.getPosition();
        
        double dx = projPos.x - position.x;
        double dy = projPos.y - position.y;
        
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
        this.position = new Vector2(x,y);
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
    public double calculateGravityAt(Vector2 point) {
        double dx = position.x - point.x;
        double dy = position.y - point.y;

        double distance = Math.sqrt(dx * dx + dy * dy);
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
        return position.x; 
    }
   
    public double getY() {
        return position.y; 
    }
    
    public Vector2 getPosition() {
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

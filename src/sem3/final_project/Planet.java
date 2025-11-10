/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import java.awt.Point;

/**
 * Represents a planet object in the game
 * A planet has a position, radius, color, name and may be a target planet
 * it can interact with projectile (collision detection, gravity, etc.. )
 * @author Vedika
 */
class Planet {
    private Point position; 
    private int radius;
    private boolean target; 
    private String color; 
    private String name; 
    
    /**
     * Checks if a projectile has hit this planet 
     * Collision occurs when the distance between centers is less, then the sum of radius (2 circles overlap or touch)
     * @param projectile what is being launched
     * @return true if there is a collision (hit), false otherwise
     */
    public boolean checkHit(Projectile projectile ) {
        double distance = getDistanceFromProjectile(projectile);
        return distance <= (this.radius + projectile.getRadius());
    }
    
    public double getDistanceFromProjectile(Projectile p) {
        //need to be different for varaition of x and y , and use square root 
    }
    
    public void generateRandomPosition(int level) {
        
    }
    /**
     * Increase the planet's radius by a scaling factor 
     * @param factor 
     */
    public void increaseRadiusFactor(double factor) {
        this.radius = (int) (radius * factor);
    }
    
    public void decreaseRadiusFactor(double factor ) {
        
    }
    
    public double calculateGravityAt(Point pos) {
        
    }
    
    public void render (GrapahicsContext gc) {
        
    }
    
    //getter and setters 
    
}

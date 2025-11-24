/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;

/**
 * Represents a planet in the MODEL layer of the game
 * A planet stores only data (position, radius, name etc)
 * and provides collision/ hit detection logic for the physics after
 * @author Vedika
 */
class Planet {

    private String name; 
    private Point2D position; 
    private int radius; 
    private boolean isTarget; 
    private String color; 
    private Point2D coordinates; 
    private double distance; 
    private double mass; 
    
    /**
     * Constructor that creates a new planet 
     * @param name name of the planet 
     * @param position position as Point2D (X,Y)
     * @param radius collision radius 
     * @param mass used when gravity is applied 
     * @param color for the View when deciding how to draw
     * @param isTarget whether this planet is the target for the current level
     */
    public Planet (String name, Point2D position, double radius, double mass, String color, boolean isTarget) {
        this.name = name; 
        this.position = position; 
        this.mass = mass; 
        this.color = color; 
        this.isTarget = isTarget; 
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
       return position.getY();
    }

    public double getMass() {
        return mass; 
    }
    
    public Point2D getPosition() {
        return position;
    }
    
    public boolean isTarget() {
        return isTarget; 
    }
    
    public String getName() {
        return name; 
    }
    
    public String getColor() {
        return color; 
    }
    
    //methods
    public boolean checkHit(Projectile projectile) {
        boolean isHit = false; 
        if (isHit ) {
            return true; 
        }
        else{
            return false; 
        }
    }
    /**
     * Distance from projectile to this planet's center
     * @param projectile the projectile to measure 
     * @return distance in the units of the game
     */
    public double getDistanceFromProjectile(Projectile projectile) {
        return projectile.getPosition().distance(position);
    }
    
    /**
     * Randomize planet position based on level of difficulty
     * Higher levels place planets more far 
     * @param level 
     */
    public void generateRandomPosition(int level) {
       double min = 100 * level;
        double max = 300 * level;
        double x = min + Math.random() * (max - min);
        double y = min + Math.random() * (max - min);
        this.position = new Point2D(x, y);
    }
    
    /**
     * increase radius to make the planet easier to hit 
     * @param factor multiplier for size increase
     */
    public void increaseRadiusFactor(double factor) {
        this.radius *= factor; 
    }
    
    /**
     * Decrease radius to make the planet harder to hit 
     * @param factor factor divison for size reduction 
     */
    public void decreaseRadiusFactor(double factor) {
        this.radius /= factor; 
    }
    
    /**
     * Calculate gravitational influence on a projectile
     * 
     * @param Projectilepos
     * @return gravity strength value
     */
    public double calculateGravityAt(Point Projecitlepos) {
        
    }
    
    public void render(GraphicsContext gc) {
        /////////////////
    }
    
    @Override
    public String toString() {
        return "Planet{name=" + name +
               ", x=" + getX() +
               ", y=" + getY() +
               ", radius=" + radius +
               ", target=" + isTarget + "}";
    }
}

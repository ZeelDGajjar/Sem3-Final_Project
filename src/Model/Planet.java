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
    
    public double getDistanceFromProjectile(Projectile p) {
        return distance;
    }
    
    public void generateRandomPosition(int level) {
       //position =  Math.random() * 3; 
    }
    
    public void increaseRadiusFactor(double factor) {
        
    }
    
    public void decreaseRadiusFactor(double factor) {
        
    }
    
    public double calculateGravityAt(Point pos) {
        //calculate the gravity 
    }
    
    public void render(GraphicsContext gc) {
        /////////////////
    }
}

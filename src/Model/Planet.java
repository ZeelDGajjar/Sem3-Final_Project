/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;

/**
 *
 * @author Vedika
 */
class Planet {
    
    private Point position; 
    private int radius; 
    private boolean target; 
    private String color; 
    private String name; 
    private Point2D coordinates; 
    private double distance; 

    double getX() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    double getY() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    double getMass() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        
    }
    
    public void render(GraphicsContext gc) {
        
    }
}

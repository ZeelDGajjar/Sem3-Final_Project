/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import java.util.Vector;
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
    
    public Projectile(double speed, double angle, double mass, Vector position, Vector velocity) {
        this.speed = speed;
        this.angle = angle;
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
    }
    
//    public List<Point> calculateTrajectory(List<Planet> planets) {
//    }
//    
//    public double calculateRange(List<Planet> planets) {
//        return 
//    }
//    
//    public double calculateTimeOfFlight(List<Planet> planets) {
//    }
//    
//    public double clculateMaxHeight(List<Planet> planets) {
//    }
//    
//    public Point updatePosition(double time, List<Planet> planets) {
//    }
//    
//    public void reset() {}
}

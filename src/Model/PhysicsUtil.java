/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.effect.Light;

/**
 *
 * @author zeelg
 */
public class PhysicsUtil {
    private double G = 6.67430E-11;
    
    /**
     * Calculates trajectory class
     * @param speed the given speed
     * @param angledeg the given angle
     * @param t 
     * @param planets
     * @param initialPosition
     * @return 
     */
    public Point2D trajectory(double speed, double angledeg, double t, List<Planet> planets, Light.Point initialPosition) {
        double x = initialPosition.getX() + calculateHorizontal(speed, angledeg, t, planets);
        double y = initialPosition.getY() + calculateVertical(speed, angledeg, t, planets);
        return new Point2D(x,y);
    }
    
    /**
     * 
     * @param p
     * @param planet
     * @return 
     */
    public static double gravityEffect(Projectile p, Planet planet) {
        double G = 6.67430e-11;
        double dx = planet.getX() - p.getX();
        double dy = planet.getY() - p.getY();
        double distance = Math.sqrt(dx*dx + dy*dy);
        if(distance == 0){
            return 0;
        }
        return G * p.getMass() * planet.getMass() / (distance * distance);
    }
    
    /**
     * 
     * @param speed
     * @param angle
     * @param t
     * @param planets
     * @return 
     */
    public double calculateHorizontal(double speed, double angle, double t, List<Planet> planets) {
        double angleRad = Math.toRadians(angle);
        double horizontalSpeed = speed * Math.cos(angle);
        
        double ax = 0;
        for (Planet planet : planets) {
            ax += gravityEffect(new Projectile(0, 0, 0, 0), planet);
        }
        return horizontalSpeed * t + 0.5 * ax * t * t;
    }
    
    /**
     * 
     * @param speed
     * @param angle
     * @param t
     * @param planets
     * @return 
     */
    private double calculateVertical(double speed, double angle, double t, List<Planet> planets) {
        double angleRad = Math.toRadians(angle);
        double verticalSpeed = speed * Math.sin(angle);
        
        double ax = 0;
        for (Planet planet : planets) {
            ax += gravityEffect(new Projectile(0, 0, 0, 0), planet);
        }
        return verticalSpeed * t + 0.5 * ax * t * t;
    }
}

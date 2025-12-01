package Model;

import java.util.List;
import javafx.geometry.Point2D;

/**
 * The physics utility class
 * @author zeelg
 */
public class PhysicsUtil {
    private static final double G = 6.67430E-11;
    
    /**
     * Computes the acceleration pulling force by finding acceleration of all planets and adding together
     * @param mass the given mass to travel through space
     * @param position the given position to compute acceleration from
     * @param planets the given list of planets to take into account
     * @return an acceleration vector of form Vector2
     */
    public static Vector2 computeGravity(double mass, Vector2 position, List<Planet> planets) {
        Vector2 total  = new Vector2(0, 0);
        
        for (Planet planet : planets) {
            Vector2 direction = new Vector2(planet.getX() - position.x, planet.getY() - position.y);
            
            double distance = direction.magnitude();
            
            if(distance == 0) continue;
            
            double force = (G * mass * planet.getMass()) / (distance * distance);
            
            Vector2 acceleration = direction.normalize().scale(force / mass);
            
            total = total.add(acceleration);
        }
        
        return total;
    }
    
    /**
     * Calculates trajectory and returns points 
     * @param speed the given speed
     * @param angledeg the given angle in degrees
     * @param t the given time 
     * @param planets the given list of planets
     * @param initialPosition the given initial position vector
     * @param mass the given mass
     * @return the next 2d point based on the path of trajectory
     */
    public static Point2D trajectory(double speed, double angledeg, double t, List<Planet> planets, Vector2 initialPosition, double mass) {
        double dt = 0.02;
        
        Vector2 position = new Vector2(initialPosition.x, initialPosition.y);
        Vector2 velocity = new Vector2(speed * Math.cos(Math.toRadians(angledeg)), speed * Math.sin(Math.toRadians(angledeg)));
        
        for(double time = 0; time < t; time += dt) {
            Vector2 acceleration = computeGravity(mass, position, planets);
            
            velocity = velocity.add(acceleration.scale(dt));
            position = position.add(velocity.scale(dt));
        }
        
        return new Point2D(position.x, position.y);
    }
}

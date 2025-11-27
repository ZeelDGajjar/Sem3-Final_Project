/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *Utility class for collision detection between : 
 * projectiles and planets in the game
 * @author Vedika
 */
public class CollisionUtil {
    
    /**
     * Checks whether a projectile hits a specific planet 
     * collision occurs when distance (projectile, planet) is less than or equal to planet radius
     * @param projectile projectile to check 
     * @param planet planet to test collision with 
     * @return true if projectile hits the planet 
     */
    public static boolean checkCollision(Projectile projectile, Planet planet) {
         double dx = projectile.getX() - planet.getX();
        double dy = projectile.getY() - planet.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        double planetRadius = planet.getRadius();

        return distance <= planetRadius; 
    }
    
     /**
     * Checks collision between the projectile and a list of planets.
     * used in GameModel.simulateLaunch()
     * @param projectile projectile to check
     * @param planets list of planets
     * @return the planet that was hit, or null if none
     */
    public static Planet checkAnyCollsion(Projectile projectile, List<Planet>planets) {
        for (Planet planet : planets) {
            if (checkCollision(projectile, planet)) {
                return planet; 
            }
        }
        return null; 
    }
    
    /**
     * Checks if a projectile leaves game boundaries.
     * This is used to determine Zombied or a miss.
     * @param projectile projectile to check
     * @param width screen width
     * @param height screen height
     * @return true if projectile is outside boundaries
     */
    public static boolean isOutOfBounds(Projectile projectile, double width, double height) {
        double x = projectile.getX();//
        double y = projectile.getY();//

        return (x < 0 || x > width || y < 0 || y > height);
    }
}

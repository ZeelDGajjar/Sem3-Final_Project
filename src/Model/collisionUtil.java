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


public class collisionUtil {
    
    /**
     * Checks whether a projectile hits a specific planet 
     * Collision = distance between centers is less or equal to radius
     * @param projectile projectile to check 
     * @param planet planet to test collision with 
     * @return true if projectile hits the planet 
     */
    public static boolean checkCollision(Projectile projectile, Planet planet) {
        
    }
    
     /**
     * Checks collision between the projectile and a list of planets.
     * Used in GameModel.simulateLaunch()
     * @param projectile projectile to check
     * @param planets list of planets
     * @return the planet that was hit, or null if none
     */
    public static Planet checkAnyCollsion(Projectile projectile, List<Planet>planet) {
        for (Planet planet : planets) {
            if (checkCollision(projectile, planet)) {
                return planet; 
            }
        }
        return null; 
    }
}

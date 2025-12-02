package Model;

import java.util.*;
import java.util.List;
import javafx.geometry.Point2D;

/**
 * The projectile class that calculates the projectile motion of the rocket
 * Fixed physics updates and constructor handling.
 * @author zeelg (updated)
 */
public class Projectile {
    private double mass;
    private Vector2 position;
    private Vector2 velocity;

    private double dt = 0.02;

    public Projectile(double mass, Vector2 initialPosition, double speed, double angle) {
        this.mass = mass;
        this.position = new Vector2(initialPosition.x, initialPosition.y);
        this.velocity = new Vector2(speed * Math.cos(Math.toRadians(angle)), speed * Math.sin(Math.toRadians(angle)));
    }

    public Projectile(double speed, double angle, double mass, Vector2 initialPosition) {
        this.mass = mass;
        this.position = new Vector2(initialPosition.x, initialPosition.y);
        this.velocity = new Vector2(speed * Math.cos(Math.toRadians(angle)), speed * Math.sin(Math.toRadians(angle)));
    }

    /**
     * Simulates full trajectory based on planets given
     * @param planets the list of planets given
     * @return a list of points in 2d space
     */
    public List<Point2D> calculateTrajectory(List<Planet> planets) {
        List<Point2D> points = new ArrayList<>();

        Vector2 simPosition = new Vector2(position.x, position.y);
        Vector2 simVelocity = new Vector2(velocity.x, velocity.y);

        for (int i = 0; i < 2000; i++) {
            Vector2 acceleration = PhysicsUtil.computeGravity(mass, simPosition, planets);

            simVelocity = simVelocity.add(acceleration.scale(dt));
            simPosition = simPosition.add(simVelocity.scale(dt));

            points.add(new Point2D(simPosition.x, simPosition.y));
        }

        return points;
    }

    /**
     * Calculates the time of the flight based on trajectory
     * @param planets the given list of planets
     * @return a double value
     */
    public double calculateTimeOfFlight(List<Planet> planets) {
        return calculateTrajectory(planets).size() * dt;
    }

    /**
     * Calculates range of the trajectory based on given planets
     * @param planets the given list of planets
     * @return a double value of range
     */
    public double calculateRange(List<Planet> planets) {
        List<Point2D> trajectory = calculateTrajectory(planets);
        if (trajectory.isEmpty()) return 0;
        return trajectory.get(trajectory.size() - 1).getX();
    }

    /**
     * Calculates the max Height based on a given planet
     * @param planets the given list of planets
     * @return a double value of the max height
     */
    public double calculateMaxHeight(List<Planet> planets) {
        List<Point2D> t = calculateTrajectory(planets);
        if (t.isEmpty()) return 0;

        double max = Double.NEGATIVE_INFINITY;

        for (Point2D p : t) {
            if (p.getY() > max) max = p.getY();
        }
        return max;
    }

    /**
     * Updates position and advance the projectile one time-step
     * @param dt the given delta time
     * @param planets the given list of planets
     * @return the next point based on acceleration in 2d
     */
    public Point2D updatePosition(double dt,  List<Planet> planets) {
        Vector2 acceleration = PhysicsUtil.computeGravity(mass, position, planets);

        velocity = velocity.add(acceleration.scale(dt));
        position = position.add(velocity.scale(dt));

        return new Point2D(position.x, position.y);
    }

    /**
     * Resets the original position
     */
    public void reset() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
    }

    // Setters and Getters
    public Vector2 getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocity(Vector2 vel) {
        this.velocity = vel;
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }
}

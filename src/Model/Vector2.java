/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Assistant vector class to help with calculations
 * @author zeelg
 */
public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a vector to another
     * @param v the given vector
     * @return the resultant vector of addition of both vectors
     */
    public Vector2 add(Vector2 v){
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    /**
     * Subtracts two vectors
     * @param v the given vector to subtract from the original
     * @return the resultant vector of subtraction of one from the other
     */
    public Vector2 subtract(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    /**
     * Scales a vector by a given factor
     * @param s the given vector
     * @return the resultant scaled vector
     */
    public Vector2 scale(double s){
        return new Vector2(this.x * s, this.y * s);
    }

    /**
     * Returns the magnitude of this vector
     * @return the calculated magnitude
     */
    public double  magnitude(){
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalizes the vector enlarged by scaling
     * @param n the given factor to undo the scaling for
     * @return the given original vector
     */
    public Vector2 normalize(double n){
        return (magnitude() == 0) ? new Vector2(0, 0) : new Vector2(x / n, y / n);
    }
}

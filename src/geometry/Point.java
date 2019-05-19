package geometry;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

/**
 * geometry.Point class
 * with the characteristics x value and y value
 * include the constructors functions, gets and sets distance and equals function.
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x position
     * @param y position
     *          constructor
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other point
     * @return distance -- return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance = Math.sqrt(Math.pow((this.x - other.getX()), 2)
                + Math.pow((this.y - other.getY()), 2));
        return distance;
    }

    /**
     * @param other point
     * @return equals - return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * @return the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param x1 the new x val
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * @param y1 the new y val
     */
    public void setY(double y1) {
        this.y = y1;
    }
}
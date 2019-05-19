package collision;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import geometry.Point;

/**
 * collision.Velocity class
 * with the characteristics dx and dy wich show a distance
 * include the constructors functions, gets and sets functions and moveOneStep function
 * collision.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * @param dx distance dx
     * @param dy distance dy
     *           constructor
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle double
     * @param speed double
     * @return create a new velocity variable using angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newAngle = Math.toRadians(angle);
        double dx = Math.sin(newAngle) * speed;
        double dy = Math.cos(newAngle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param dx1 the new dx value
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * @param dy1 the new dy value
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * @param p point
     * @return Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + (this.dx);
        double newY = p.getY() + (this.dy);
        Point newP = new Point(newX, newY);
        return newP;
    }
}
package geometry; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import spirtes.Sprite;

import java.awt.Color;

/**
 * geometry.Ball class
 * with the characteristics size of the ball,his color,his center location,his velocity and thee border he is in to
 * include the constructors functions, gets and sets functions and moveOneStep function.
 */
public class Ball implements Sprite {
    private int size;
    private java.awt.Color color;
    private Point location;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * @param center a location of the point
     * @param r      the size r of the ball
     * @param color  the color of the ball
     *               constructor that gets geometry.Point,radius and a color and built a ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.size = r;
        this.color = color;
        this.location = center;
    }

    /**
     * @param x     x damnation
     * @param y     y damnation
     * @param r     the radius of the ball
     * @param color color of the ball
     *              constructor that gets x and y damnation,radius and a color and built a ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.location = new Point(x, y);
        this.size = r;
        this.color = color;
    }

    /**
     * @return the location
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * @return the y value
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * @return the size of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return the point of the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @return the game invaroment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * @param g game invaroment
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * @param dx velocity value
     * @param dy velocity value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @param v a velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the location.
     *
     * @param location1 a location
     */
    public void setLocation(Point location1) {
        this.location = location1;
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @param surface the surface e want to draw on
     *                draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.size);
        surface.setColor(Color.WHITE);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * @param dt dt
     *           notify the sprite that time has passed.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * add the ball sprite to the game.
     *
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * @param game1 the current game
     *              remove the ball from the game
     */
    public void removeFromGame(GameLevel game1) {
        game1.removeSprite(this);
    }

    /**
     * move the ball inside the screen.
     *
     * @param dt the dt value
     */
    public void moveOneStep(double dt) {
        velocity = new Velocity(velocity.getDx() * dt, velocity.getDy() * dt);
        Line line = new Line(this.location, this.velocity.applyToPoint(this.location));
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(line);
        //case 1: collision is null.
        if (collision == null) {
            this.location = line.end();
            //new velocity
        } else {
            this.location = (new Velocity(
                    collision.collisionPoint().getX() - this.location.getX() - this.velocity.getDx(),
                    collision.collisionPoint().getY() - this.location.getY() - this.velocity.getDy())).
                    applyToPoint(this.location);
            Velocity currentVelocity = collision.collisionObject().hit(collision.collisionPoint(), this.velocity, this);
            //change the velocity.
            this.setVelocity(currentVelocity);
        }
        velocity = new Velocity(velocity.getDx() / dt, velocity.getDy() / dt);
    }
}


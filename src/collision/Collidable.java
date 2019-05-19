package collision; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * the collision.Collidable interface
 * include the 2 methods getCollisionRectangle and hit.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  the collision point with the block
     * @param currentVelocity the current velocity
     * @param hitten1         the ball that hits the block
     * @return Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitten1);


}
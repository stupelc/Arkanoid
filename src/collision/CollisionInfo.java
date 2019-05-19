package collision; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import geometry.Point;

/**
 * collision.CollisionInfo Class
 * include the characteristics collisionPoint and collisionObject.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * @param collisionPoint  a collision point
     * @param collisionObject the collision object
     *                        a constractor
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
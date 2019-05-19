package listeners; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import blocks.Block;
import geometry.Ball;

/**
 * the listeners.HitListener interface.
 */
public interface HitListener {

    /**
     * @param beingHit a block
     * @param hitter   a ball
     *                 This method is called whenever the beingHit object is hit.
     *                 The hitter parameter is the geometry.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
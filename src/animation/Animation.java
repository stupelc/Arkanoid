package animation; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;

/**
 * the animation.Animation interface.
 */
public interface Animation {

    /**
     * @param d  the drawSurface
     * @param dt dt
     *           take care of one frame
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return if the game should stopped according to the conditions
     */
    boolean shouldStop();
}
package spirtes; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;

/**
 * the spirtes.Sprite interface
 * include the 2 methods getCollisionRectangle and hit.
 */
public interface Sprite {
    /**
     * @param d a drawSurface
     *          draw the sprite to the screen
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    void timePassed(double dt);
}
package indincators; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import geometry.Rectangle;
import spirtes.Sprite;
import utils.Counter;

import java.awt.Color;

/**
 * indincators.LivesIndicator class.
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    private Rectangle rectangle;

    /**
     * @param livesCounter the current lifes
     * @param rectangle    a rectangle
     */
    public LivesIndicator(Rectangle rectangle, Counter livesCounter) {
        this.livesCounter = livesCounter;
        this.rectangle = rectangle;
    }

    /**
     * @param d a drawSurface
     *          draw the sprite to the screen
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(40, 20, "Live: " + (this.livesCounter.getValue()), 20);
    }

    /**
     * @param dt dt
     *           notify the sprite that time has passed.
     */
    public void timePassed(double dt) {

    }

}

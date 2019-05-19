package indincators;
/**
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
 * indincators.ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {

    private Rectangle rectangle;
    private Counter scoreCounter;

    /**
     * @param rect         recatangle
     * @param scoreCounter a counter
     *                     constructor
     */
    public ScoreIndicator(Rectangle rect, Counter scoreCounter) {
        this.rectangle = rect;
        this.scoreCounter = scoreCounter;
    }

    /**
     * @param d a drawSurface
     *          draw the sprite to the screen
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((800 / 3), 20, "Score: " + ((this.scoreCounter.getValue())), 20);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt dt
     */
    public void timePassed(double dt) {
    }
}

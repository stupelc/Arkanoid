package indincators;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import geometry.Rectangle;
import spirtes.Sprite;

import java.awt.Color;

/**
 * indincators.ScoreIndicator class.
 */
public class NameIndicator implements Sprite {

    private Rectangle rectangle;
    private String name;

    /**
     * @param rect a rectangle
     * @param name name of the level
     */
    public NameIndicator(Rectangle rect, String name) {
        this.rectangle = rect;
        this.name = name;
    }

    /**
     * @param d a drawSurface
     *          draw the sprite to the screen
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(450, 20, "Level Name: " + (this.name), 20);
    }

    /**
     * @param dt dt
     *           notify the sprite that time has passed.
     */
    public void timePassed(double dt) {
    }
}

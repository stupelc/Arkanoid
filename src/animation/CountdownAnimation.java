package animation; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import collision.SpriteCollection;

import java.awt.Color;

/**
 * The animation.CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int countFramesTillStop = 0;

    /**
     * Constructors.
     *
     * @param numOfSeconds - the function gets the number of seconds per count.
     * @param countFrom    - the function gets the number of seconds which will count.
     * @param gameScreen   - the function gets the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * @param d  a drawSurface - the function gets draw surface to draw on the screen.
     * @param dt dt
     *           The function draw the count down.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Draw all the sprites.
        this.gameScreen.drawAllOn(d);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // Set the color of the count down.
        d.setColor(Color.WHITE);

        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 + 2, d.getHeight() / 2 + 100, "" + countFrom, 48);
        d.drawText(d.getWidth() / 2 - 2, d.getHeight() / 2 + 100, "" + countFrom, 48);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 102, "" + countFrom, 48);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 98, "" + countFrom, 48);

        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 100, "" + countFrom, 48);

        // Skip the first waiting.
        if (countFramesTillStop == 0) {
            countFramesTillStop++;
        } else {
            sleeper.sleepFor(1000);
            countFrom--;
        }
    }

    /**
     * @return - returns true if the count down is over , false otherwise.
     */
    public boolean shouldStop() {
        if (countFrom < 0) {
            return true;
        }
        return false;
    }
}
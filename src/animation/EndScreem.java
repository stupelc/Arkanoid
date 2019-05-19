package animation;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * the endScreem class.
 */
public class EndScreem implements Animation {

    private int finalScore;
    private KeyboardSensor keyboard;
    private boolean status;
    private boolean stop;


    /**
     * @param finalScore     the final score of the player
     * @param keyboardSensor the keyboard sensor
     * @param status         the status of the player - win or lose
     */
    public EndScreem(int finalScore, KeyboardSensor keyboardSensor, boolean status) {
        this.finalScore = finalScore;
        this.stop = false;
        this.keyboard = keyboardSensor;
        this.status = status;
    }

    /**
     * @param d  a drawSurface a drawSurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //the player win
        if (this.status) {
            d.setColor(Color.decode("#F5B7D8"));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLACK);
            d.drawText(50, d.getHeight() / 2 - 100, "You Win! Your score is " + this.finalScore, 50);
        } else {
            d.setColor(Color.decode("#F5B7D8"));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLACK);
            d.drawText(50, d.getHeight() / 2 - 100, "Game Over. Your score is " + this.finalScore, 50);
        }

        d.setColor(Color.BLACK);
        d.drawLine(20, 20, 780, 20);
        d.drawLine(20, 20, 20, 580);
        d.drawLine(20, 580, 780, 580);
        d.drawLine(780, 20, 780, 580);

    }

    /**
     * @return the stop value
     */
    public boolean shouldStop() {
        return true;
    }
}



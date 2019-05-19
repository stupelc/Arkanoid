package animation; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * the pauseScreen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * @param k the KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * @param ds a drawSurface a drawSurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface ds, double dt) {
        ds.setColor(Color.LIGHT_GRAY);
        ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());


        ds.setColor(Color.GRAY);
        ds.fillCircle(400, 300, 100);
        ds.setColor(Color.WHITE);
        ds.fillCircle(400, 300, 98);
        ds.setColor(Color.decode("#1788d0"));
        ds.fillCircle(400, 300, 90);
        ds.setColor(Color.GRAY);
        ds.fillCircle(400, 300, 88);
        ds.setColor(Color.BLACK);
        ds.fillCircle(400, 300, 86);


        ds.setColor(Color.GRAY);
        ds.fillRectangle(350, 250, 40, 100);
        ds.fillRectangle(410, 250, 40, 100);

        int offset = 3;


        ds.setColor(Color.decode("#1788d0"));
        ds.fillRectangle(350 + offset, 250 + offset, 40 - offset, 100 - offset);
        ds.fillRectangle(410 + offset, 250 + offset, 40 - offset, 100 - offset);


        String msg = "Press space to continue";

        ds.setColor(Color.BLACK);
        ds.drawText(199, 500, msg, 32);
        ds.setColor(Color.decode("#1788d0"));
        ds.drawText(200, 501, msg, 32);
        ds.setColor(Color.BLACK);
        ds.drawText(202, 502, msg, 32);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * @return the stop value
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
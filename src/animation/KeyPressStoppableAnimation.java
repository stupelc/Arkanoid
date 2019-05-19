package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * animation.KeyPressStoppableAnimation Class.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isPressed;

    /**
     * @param sensor    the keyboard
     * @param key       the string
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.stop = false;
        this.isPressed = true;
    }

    /**
     * @param d  the drawSurface
     * @param dt dt
     *           take care of one frame
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isPressed) {
                this.stop = true;
            }
        } else {
            this.isPressed = false;
        }
    }

    /**
     * @return if the game should stopped according to the conditions
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

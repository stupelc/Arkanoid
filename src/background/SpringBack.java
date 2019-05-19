package background;

import biuoop.DrawSurface;
import spirtes.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * background.SpringBack Class.
 */
public class SpringBack implements Sprite {
    private BufferedImage image;

    /**
     * the constructor.
     */
    public SpringBack() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/background_images/spring.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param d a drawSurface
     *          draw the sprite to the screen
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(20, 25, this.image);
    }

    /**
     * @param dt dt
     *           notify the sprite that time has passed.
     */
    public void timePassed(double dt) {

    }

}

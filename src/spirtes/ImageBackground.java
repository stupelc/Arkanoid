package spirtes;

import java.awt.Image;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;

/**
 * Draws image at given position.
 */
public class ImageBackground implements Fill {

    private Image image;

    /**
     * Constructor.
     *
     * @param bg name and location of the file.
     */
    public ImageBackground(String bg) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(bg);
            this.image = ImageIO.read(is);
        } catch (Exception e) {
            System.out.println("cannot find image");
        }
    }

    /**
     * Draws the image as the background.
     *
     * @param d      drawsurface
     * @param xpos   x-position.
     * @param ypos   y-position.
     * @param width  width.
     * @param height height.
     */
    public void fillInRectangle(DrawSurface d, int xpos, int ypos, int width, int height) {
        d.drawImage(xpos, ypos, this.image);
    }

    /**
     * Does nothing.
     *
     * @param dt speed to go by
     */
    public void timePassed(double dt) {
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }
}

package spirtes;

import biuoop.DrawSurface;

/**
 * how to fill a rectangle.
 */
public interface Fill extends Sprite {

    /**
     * fills it in.
     * @param d drawsurface
     * @param xpos xposition
     * @param ypos yposition
     * @param width width
     * @param height height
     */
    void fillInRectangle(DrawSurface d, int xpos, int ypos, int width, int height);
}

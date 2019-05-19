package blocks;

import geometry.Point;
import geometry.Rectangle;
import spirtes.Fill;

import java.awt.Color;

/**
 * Holds info of how to create block at given location.
 */
public class AbstractBlockCreator implements BlockCreator {

    private int width;
    private int height;
    private int hitPoints;
    private Fill[] colors;
    private Color stroke;

    /**
     * Constructor.
     *
     * @param width     width
     * @param height    height
     * @param hitPoints hitPoint
     * @param colors    for each hitPoint
     * @param stroke    border
     */
    public AbstractBlockCreator(int width, int height, int hitPoints, Fill[] colors, Color stroke) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.colors = colors;
        this.stroke = stroke;
    }

    /**
     * Creates new block.
     *
     * @param xpos x-position
     * @param ypos y-position
     * @return new block
     */
    public Block create(int xpos, int ypos) {
        Rectangle newRect = new Rectangle(new Point(xpos, ypos), this.width, this.height);
        return new Block(newRect, this.hitPoints, this.colors, this.stroke);
    }

    /**
     * @return width of block.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return height of block.
     */
    public int getHeight() {
        return this.height;
    }
}

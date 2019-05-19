package geometry; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import java.awt.Color;
/**
 * geometry.Border class
 * with the characteristics point of the bottomRight and a point of the topLeft and a color
 * include the constructors functions, gets and sets functions and draw on function.
 */
public class Border {
    private Point bottomRight;
    private Point topLeft;
    private Color color;

    /**
     *
     * @param x1 x1 position
     * @param y1 y1 position
     * @param x2 x2 position
     * @param y2 y2 position
     * @param color color of the border
     * constructor that gets 4 positions and a color and built a border
     */
    public Border(int x1, int y1, int x2, int y2, Color color) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.topLeft = p1;
        this.bottomRight = p2;
        this.color = color;
    }

    /**
     *
     * @param surface we are going to draw on this surface
     * draw the geometry.Rectangle on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.topLeft.getX(), (int) this.topLeft.getY(),
                (int) this.bottomRight.getX(), (int) this.bottomRight.getY());
    }
}

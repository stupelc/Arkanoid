package spirtes;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Draws color at given location with given parameters.
 */
public class SingleColorBackground implements Fill {

    private Color color;

    /**
     * Constructor.
     *
     * @param bg color to create and use
     */
    public SingleColorBackground(String bg) {
        //checks if it starts with RGB
        if (bg.startsWith("RGB")) {

            //trims it and splits into 3 integers
            String temp = bg.substring(bg.indexOf("(") + 1, bg.length());
            String[] numbers = temp.split(",");
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            int z = Integer.parseInt(numbers[2]);

            //creates color
            this.color = new Color(x, y, z);

            //should be a color
        } else {
            try {
                java.lang.reflect.Field field = Color.class.getField(bg);
                this.color = (Color) field.get(null);
            } catch (Exception e) {
                this.color = null; // Color not defined
            }
        }
    }

    /**
     * Constructor.
     *
     * @param bg color to create and use
     */
    public SingleColorBackground(Color bg) {
        this.color = bg;
    }

    /**
     * Draws the color in a rectangle.
     *
     * @param d      drawsurface
     * @param xpos   x-position.
     * @param ypos   y-position.
     * @param width  width.
     * @param height height.
     */
    public void fillInRectangle(DrawSurface d, int xpos, int ypos, int width, int height) {
        d.setColor(this.color);
        d.fillRectangle(xpos, ypos, width, height);
    }

    /**
     * Does nothing.
     *
     * @param dt time to go by
     */
    public void timePassed(double dt) {
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, 800, 600);
    }

}

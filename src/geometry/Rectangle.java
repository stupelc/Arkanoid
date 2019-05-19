package geometry;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * geometry.Rectangle class
 * with the characteristics upperLeft point, width and height of the rectangle
 * include the constructors functions, gets and sets functions and intersectionPoints function.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line on;
    private Line under;
    private Line right;
    private Line left;
    private Color color;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the point in the rectangle upperLeft
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        Random rand = new Random();
        int red = rand.nextInt();
        int green = rand.nextInt();
        int blue = rand.nextInt();

        this.color = color;

        //i creates the 3 point witch include the rectangle i have
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        //now that i have the points i will create the lines of the rectangle
        this.on = new Line(this.upperLeft, upperRight);
        this.right = new Line(upperRight, lowerRight);
        this.under = new Line(lowerLeft, lowerRight);
        this.left = new Line(this.upperLeft, lowerLeft);
    }

    /**
     * create a rectangle using a point and a width and height demination.
     *
     * @param upperLeft the point in the rectangle upperLeft
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        Random rand = new Random();
        int red = rand.nextInt();
        int green = rand.nextInt();
        int blue = rand.nextInt();

        //i creates the 3 point witch include the rectangle i have
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        //now that i have the points i will create the lines of the rectangle
        this.on = new Line(this.upperLeft, upperRight);
        this.right = new Line(upperRight, lowerRight);
        this.under = new Line(lowerLeft, lowerRight);
        this.left = new Line(this.upperLeft, lowerLeft);
    }

    /**
     * @param line the line we want to check
     * @return Return a (possibly empty) List of intersection point with the specified line.
     */
    public java.util.List intersectionPoints(Line line) {
        //now i will check if there is any intersection points with the specified line.
        List<Point> intersectionPoints = new ArrayList<Point>();
        if (this.on.isIntersecting(line)) {
            intersectionPoints.add(this.on.intersectionWith(line));
        }
        if (this.under.isIntersecting(line)) {
            intersectionPoints.add(this.under.intersectionWith(line));
        }
        if (this.left.isIntersecting(line)) {
            intersectionPoints.add(this.left.intersectionWith(line));
        }
        if (this.right.isIntersecting(line)) {
            intersectionPoints.add(this.right.intersectionWith(line));
        }

        return intersectionPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the "on" line
     */
    public Line getOn() {
        return on;
    }

    /**
     * @return the "under" line
     */
    public Line getUnder() {
        return under;
    }

    /**
     * @return the "left" line
     */
    public Line getLeft() {
        return left;
    }

    /**
     * @return the "right" line
     */
    public Line getRight() {
        return right;
    }

    /**
     * set the color of the rectangle.
     *
     * @param color1 a color
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * @param height1 the new height.
     */
    public void setHeight(double height1) {
        this.height = height1;
    }


    /**
     * @param surface we are going to draw on this surface
     *                draw the geometry.Rectangle on the given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);

        surface.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) (this.getWidth()),
                (int) (this.getHeight()));

        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) (this.getWidth()),
                (int) (this.getHeight()));
    }

//    /**
//     * @param surface surface
//     * @param obgect  background that can be Image or Color
//     *                draw the block according to the Backgroung we have got
//     */
//    public void drawOnAcoordingBack(DrawSurface surface, Object obgect) {
//
//        if (obgect instanceof Image) {
//            spirtes.ImageBackground imageBackground = new spirtes.ImageBackground((Image) obgect);
//            imageBackground.drawOn(surface, this);
//        }
//        if (obgect instanceof Color) {
//            spirtes.SingleColorBackground singleColorBackground = new spirtes.SingleColorBackground((Color) obgect);
//            singleColorBackground.drawOn(surface, this);
//        }
//    }


}
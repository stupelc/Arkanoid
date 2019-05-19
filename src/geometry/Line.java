package geometry; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import java.util.List;

/**
 * geometry.Line class
 * with the characteristics size of the ball,his color,his center location,his velocity and thee border he is in to
 * include the constructors functions, gets and sets functions and moveOneStep function.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * @param start a start point
     * @param end   an end point
     *              constructors that gets 2 points and create a line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @param x1 x1 position
     * @param y1 y1 position
     * @param x2 x2 position
     * @param y2 y2 position
     *           constructor that gets 4 position and creates a line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other a line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //first we will check if the lines parallel
        //this is the case when the the line is parallel to y axis
        if ((this.end.getX() - this.start.getX()) == 0 || (other.end.getX() - other.start.getX()) == 0) {
            if ((this.end.getX() - this.start.getX()) == 0 && (other.end.getX() - other.start.getX()) == 0) {
                return false;
            }
        } else {
            double mThis = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            if (other.end.getX() - other.start.getX() != 0) {
                double mOther = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
                //if the incline equals the lines are parallel and they aren't meet
                if (mThis == mOther) {
                    return false;
                }
            }
        }

        //now i get the cut point from the function "intersectionWith"
        Point cut = this.intersectionWith(other);
        //check if the cut point is in the range
        if (isBetween(cut, this) && isBetween(cut, other)) {
            return true;
        }
        return false;
    }

    /**
     * @param other a line
     * @return he intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //start value
        double x = 0, y = 0;

        if ((this.end.getX() - this.start.getX()) == 0 || (other.end.getX() - other.start.getX()) == 0) {
            if (this.end.getX() - this.start.getX() == 0) {
                x = this.start.getX();
            } else {
                x = other.start.getX();
            }
            if ((this.end.getX() - this.start.getX()) == 0 && (other.end.getX() - other.start.getX()) != 0) {
                double mOther = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
                y = ((mOther * (x - other.start.getX())) + other.start.getY());
            } else {
                if ((this.end.getX() - this.start.getX()) != 0 && (other.end.getX() - other.start.getX()) == 0) {
                    double mThis = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
                    y = mThis * x - mThis * this.start.getX() + this.start.getY();
                } else {
                    //other is makbil y
                    if (other.end.getY() - other.start.getY() == 0 && (this.end.getX() - this.start.getX()) == 0) {
                        y = other.start.getY();
                    } else {
                        if (this.end.getY() - this.start.getY() == 0 && (other.end.getX() - other.start.getX()) == 0) {
                            y = this.start.getY();
                        }
                    }
                }
            }
        } else {
            //now we will find the point where the 2 lines meets
            double mThis = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double mOther = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());

            x = (mThis * this.start.getX() - mOther * other.start.getX() - this.start.getY() + other.start.getY())
                    / (mThis - mOther);
            y = mThis * x - mThis * this.start.getX() + this.start.getY();
        }
        //create the cut points between the 2 lines
        Point cut = new Point(x, y);
        return cut;
    }

    /**
     * @param other line type
     * @return equals -- return true is the lines are equal, false otherwise
     */
    private boolean equals(Line other) {
        if ((this.start.getX() == other.start.getX() && this.start.getY() == other.end.getY())
                || (this.start.getX() == other.end.getX() && this.end.getY() == other.start.getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param point a point
     * @param line  a line
     * @return 'true' if the point is in the range of the line and 'false' otherwise
     */
    public boolean isBetween(Point point, Line line) {
        if ((point.getX() >= Math.min(line.start.getX(), line.end.getX())
                && (point.getX() <= Math.max(line.start.getX(), line.end.getX())))
                && (point.getY() >= Math.min(line.start.getY(), line.end.getY())
                && (point.getY() <= Math.max(line.start.getY(), line.end.getY())))) {
            return true;
        }
        return false;
    }

    /**
     * @param rect a specific rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //first i will check if the line intersect with the rectangle
        List<Point> intersectioPoints = rect.intersectionPoints(this);
        //there werent any intersect points with the rectangle
        if (intersectioPoints.size() == 0) {
            return null;
        } else {
            double min = intersectioPoints.get(0).distance(this.start);
            Point minPoint = intersectioPoints.get(0);
            for (int i = 1; i < intersectioPoints.size(); i++) {
                if (intersectioPoints.get(i).distance(this.start) < min) {
                    min = intersectioPoints.get(i).distance(this.start);
                    minPoint = intersectioPoints.get(i);
                }
            }
            return minPoint;
        }
    }

    /**
     * @param collisionPoint the meet geometry.Point
     * @return 'true' if the point is in the range of the line and 'false' otherwise
     */
    public boolean pointIntersectionWith(Point collisionPoint) {
        if ((collisionPoint.getX() >= Math.min(this.start.getX(), this.end.getX())
                && (collisionPoint.getX() <= Math.max(this.start.getX(), this.end.getX())))
                && (collisionPoint.getY() >= Math.min(this.start.getY(), this.end.getY())
                && (collisionPoint.getY() <= Math.max(this.start.getY(), this.end.getY())))) {
            return true;
        }
        return false;
    }
}
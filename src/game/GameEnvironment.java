package game; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * game.GameEnvironment class
 * with the characteristic list from collidable
 * include the constructors functions, gets and sets functions and moveOneStep function.
 */
public class GameEnvironment {
    private List<Collidable> list = new ArrayList<>();

    /**
     * @return the collidable list.
     */
    public List<Collidable> getList() {
        return list;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * @param trajectory a line
     * @return Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        List<Collidable> temp = new ArrayList<Collidable>();
        List<Point> minPoint = new ArrayList<Point>();
        //first i enters all the min points usuing the function closestIntersectionToStartOfLine
        for (int i = 0; i < this.list.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(this.list.get(i).getCollisionRectangle()) != null) {
                minPoint.add(trajectory.closestIntersectionToStartOfLine(this.list.get(i).getCollisionRectangle()));
                temp.add(this.list.get(i));
            }
        }

        if (minPoint.size() == 0) {
            return null;
        }

        //now i will check witch of the following points is the closest to the ball
        double distance = minPoint.get(0).distance(trajectory.start());
        int minIndex = 0;
        for (int i = 1; i < minPoint.size(); i++) {
            if (minPoint.get(i).distance(trajectory.start()) < distance) {
                distance = minPoint.get(i).distance(trajectory.start());
                minIndex = i;
            }
        }
        //now i found the index of the point witch will be like tha collision point
        Collidable c = temp.get(minIndex);
        CollisionInfo collision = new CollisionInfo(minPoint.get(minIndex), c);
        return collision;

    }

}
package blocks; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import collision.Collidable;
import collision.Velocity;
import game.GameLevel;
import geometry.Ball;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;
import spirtes.Fill;
import spirtes.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * blocks.Block class (implements collision.Collidable)
 * with the characteristics size of the ball,his color,his center location,his velocity and thee border he is in to
 * include the constructors functions, gets and sets functions and moveOneStep function.
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color stroke;
    private int countHits;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Fill[] fills;


    /**
     * @param rectangle a rectangle
     * @param countHits the num of hits
     */
    public Block(Rectangle rectangle, int countHits) {
        this.rectangle = rectangle;
        this.countHits = countHits;
    }

    /**
     * @param rectangle a rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * @param rectangle a rectangle
     * @param hitPoints the num of hits
     * @param colors    the colors that we have on every count of hits
     * @param stroke    the color of the stroke
     */
    public Block(Rectangle rectangle, int hitPoints, Fill[] colors, Color stroke) {
        this.rectangle = rectangle;
        this.countHits = hitPoints;
        this.fills = colors;
        this.stroke = stroke;

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d a drawSurface
     */
    public void drawOn(DrawSurface d) {
        if (this.fills == null) {
            this.getCollisionRectangle().drawOn(d);
        } else {
            if (this.fills[this.countHits] == null) {
                this.fills[0].fillInRectangle(d,
                        (int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            } else {
                this.fills[this.countHits].fillInRectangle(d,
                        (int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            }

            if (this.stroke != null) {
                d.setColor(this.stroke);
                d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                        (int) this.rectangle.getUpperLeft().getY(),
                        (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            }
        }
    }

    /**
     * @param dt dt
     *           notify the sprite that time has passed.
     */
    public void timePassed(double dt) {
    }

    /**
     * add the ball sprite to the game.
     *
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * @param game1 the current game that we use
     *              remove a block from the game environment
     */
    public void removeFromGame(GameLevel game1) {
        //we remove the collidable and the sprite from the game
        game1.removeCollidable(this);
        game1.removeSprite(this);
    }

    /**
     * @param collisionPoint  a collision point
     * @param currentVelocity the current velocity
     * @param hitter1         the ball that hits the block
     * @return Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    public Velocity hit(geometry.Point collisionPoint, Velocity currentVelocity, Ball hitter1) {
        double collisionPointX = collisionPoint.getX();
        double collisionPointY = collisionPoint.getY();
        double newDyVelocity = currentVelocity.getDy();
        double newDxVelocity = currentVelocity.getDx();

        if ((collisionPointY >= this.rectangle.getLeft().start().getY())
                && (collisionPointY <= this.rectangle.getLeft().end().getY())
                && (collisionPointX == this.rectangle.getLeft().start().getX())) {
            newDxVelocity = -Math.abs(newDxVelocity);
        } else if ((collisionPointY >= this.rectangle.getRight().start().getY())
                && (collisionPointY <= this.rectangle.getRight().end().getY())
                && (collisionPointX == this.rectangle.getRight().start().getX())) {
            newDxVelocity = Math.abs(newDxVelocity);
        } else if ((collisionPointX <= this.rectangle.getOn().end().getX())
                && (collisionPointX >= this.rectangle.getOn().start().getX())
                && (collisionPointY == this.rectangle.getOn().start().getY())) {
            newDyVelocity = -Math.abs(newDyVelocity);
        } else if ((collisionPointX <= this.rectangle.getUnder().end().getX())
                && (collisionPointX >= this.rectangle.getUnder().start().getX())
                && (collisionPointY == this.rectangle.getUnder().start().getY())) {
            newDyVelocity = Math.abs(newDyVelocity);
        }
        this.notifyHit(hitter1);
        currentVelocity = new Velocity(newDxVelocity, newDyVelocity);
        return currentVelocity;
    }

    /**
     * @param hitter a ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @param hl a hit listener
     *           Add hl as a listener to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * @param hl a hit listener
     *           Remove hl from the list of listeners to hit events.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * @return the um of hits
     */
    public int getCountHits() {
        return countHits;
    }

    /**
     * @param countHits1 the new num of hits
     */
    public void setCountHits(int countHits1) {
        this.countHits = countHits1;
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * set the rectangle.
     *
     * @param rectangle1 a rectangle.
     */
    public void setRectangle(Rectangle rectangle1) {
        this.rectangle = rectangle1;
    }
}

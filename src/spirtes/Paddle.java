package spirtes; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Velocity;
import game.GameLevel;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;


import java.awt.Color;


/**
 * spirtes.Paddle class
 * with the characteristics keyboard, paddle from rectangle and a limit
 * include the constructors functions, gets and sets functions and moveLeft, moveRight, timePassed,
 * drawOn,addToGame and hit.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Line limit;
    private int velocityPaddle;
    private double theRealHieght;


    /**
     * a contractor.
     *
     * @param paddle         the padele rectangle
     * @param limit          the limits of the paddle
     * @param velocityPaddle the velocity of the paddle
     */
    public Paddle(Rectangle paddle, Line limit, int velocityPaddle) {
        this.paddle = paddle;
        this.limit = limit;
        theRealHieght = paddle.getHeight();
        this.velocityPaddle = velocityPaddle;
    }

    /**
     * @param dt dt
     *           move the paddle 5 spaces left.
     */
    public void moveLeft(double dt) {
        if (this.paddle.getUpperLeft().getX() > this.limit.start().getX()
                && this.paddle.getUpperLeft().getX() + this.paddle.getWidth() <= this.limit.end().getX()) {
            if (this.paddle.getUpperLeft().getX() - (this.velocityPaddle * dt) >= this.limit.start().getX()) {
                this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() - (this.velocityPaddle * dt),
                        this.paddle.getUpperLeft().getY()),
                        this.paddle.getWidth(), this.paddle.getHeight());
            } else {
                this.paddle = new Rectangle(new Point(this.limit.start().getX(),
                        this.paddle.getUpperLeft().getY()),
                        this.paddle.getWidth(), this.paddle.getHeight());
            }
        }
    }

    /**
     * @param dt dt
     *           move the paddle 5 spaces right.
     */
    public void moveRight(double dt) {

        if (this.paddle.getUpperLeft().getX() >= this.limit.start().getX()
                && this.paddle.getUpperLeft().getX() + this.paddle.getWidth() < this.limit.end().getX()) {
            if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth()
                    + (this.velocityPaddle * dt) <= this.limit.end().getX()) {
                this.paddle = new Rectangle(new Point(this.paddle.getUpperLeft().getX() + (this.velocityPaddle * dt),
                        this.paddle.getUpperLeft().getY()),
                        this.paddle.getWidth(), this.paddle.getHeight());
            } else {
                this.paddle = new Rectangle(new Point(this.limit.end().getX() - this.paddle.getWidth(),
                        this.paddle.getUpperLeft().getY()),
                        this.paddle.getWidth(), this.paddle.getHeight());
            }
        }
    }

    /**
     * @param keyboard1 a keyboard
     */
    public void setKeyboard(KeyboardSensor keyboard1) {
        this.keyboard = keyboard1;
    }

    /**
     * @param dt dt
     *           spirtes.Sprite.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * draw the paddle on the surface.
     *
     * @param d a drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);

        d.fillRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) (this.paddle.getWidth()),
                (int) (this.theRealHieght));

        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(),
                (int) (this.paddle.getWidth()),
                (int) (this.theRealHieght));
    }

    /**
     * collision.Collidable.
     *
     * @return the paddle collision
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * @return the rectangle.
     */
    public Rectangle getPaddle() {
        return paddle;
    }

    /**
     * @param collisionPoint  the point that it collided with the paddle.
     * @param currentVelocity the now velocity of the ball.
     * @param hitten1         a ball that hits the paddle
     * @return the new velocity according to the part of the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitten1) {

        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
//        if (Double.isNaN(speed)) {
//            if (currentVelocity.getDx() == 0) {
//                return new collision.Velocity(currentVelocity.getDy(), currentVelocity.getDy());
//            } else {
//                return new collision.Velocity(currentVelocity.getDx(), currentVelocity.getDx());
//            }
//        }

        double widthEachPaddle = (this.getPaddle().getWidth()) / 5;

        if (collisionPoint.getX() >= this.getPaddle().getUpperLeft().getX()
                && collisionPoint.getX() < this.getPaddle().getUpperLeft().getX() + widthEachPaddle) {
            return Velocity.fromAngleAndSpeed(240, speed);

        } else if (collisionPoint.getX() >= this.getPaddle().getUpperLeft().getX() + widthEachPaddle
                && collisionPoint.getX() < this.getPaddle().getUpperLeft().getX() + (2 * widthEachPaddle)) {
            return Velocity.fromAngleAndSpeed(210, speed);

        } else if (collisionPoint.getX() >= this.getPaddle().getUpperLeft().getX() + (2 * widthEachPaddle)
                && collisionPoint.getX() < this.getPaddle().getUpperLeft().getX() + (3 * widthEachPaddle)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());

        } else if (collisionPoint.getX() >= this.getPaddle().getUpperLeft().getX() + (3 * widthEachPaddle)
                && collisionPoint.getX() < this.getPaddle().getUpperLeft().getX() + (4 * widthEachPaddle)) {
            return Velocity.fromAngleAndSpeed(120, speed);

        } else if (collisionPoint.getX() >= this.getPaddle().getUpperLeft().getX() + (4 * widthEachPaddle)
                && collisionPoint.getX() <= (this.getPaddle().getUpperLeft().getX() + this.getPaddle().getWidth())) {
            return Velocity.fromAngleAndSpeed(150, speed);
        }
        return null;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g a game variable.
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
}
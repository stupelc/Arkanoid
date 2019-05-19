package level; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import biuoop.DrawSurface;
import blocks.Block;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;

import spirtes.Sprite;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * level.DirectHit class.
 * First level.
 */
public class DirectHit implements LevelInformation {

    /**
     * an empty constructor.
     */
    public DirectHit() {
    }

    /**
     * @return the num of balls in a specific level
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * @return The initial velocity of each ball
     * the initialBallVelocities().size() == numberOfBalls()
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<>();
        l.add(Velocity.fromAngleAndSpeed(0.0, 3.0));
        return l;
    }

    /**
     * @return the speed of the paddle
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * @return the witdh of the paddle
     */
    public int paddleWidth() {
        return 85;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {

                d.setColor(Color.BLACK);
                d.fillRectangle(0, 18, d.getWidth(), d.getHeight());

                d.setColor(Color.BLUE);
                d.drawCircle(400, 162, 60);
                d.drawCircle(400, 162, 90);
                d.drawCircle(400, 162, 120);

                d.drawLine(400, 182, 400, 302);
                d.drawLine(420, 162, 540, 162);
                d.drawLine(380, 162, 260, 162);
                d.drawLine(400, 142, 400, 22);
            }

            @Override
            public void timePassed(double dt) {

            }
        };
    }

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> l = new ArrayList<>();
        l.add(new Block(new Rectangle(new Point(385, 150), 30, 30, Color.red), 1));
        return l;
    }

    /**
     * @return Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * @return the color of the top block
     */
    public Color colorOfTheTop() {
        return Color.YELLOW;
    }

}

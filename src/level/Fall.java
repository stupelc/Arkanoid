package level;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import background.FallBack;
import biuoop.DrawSurface;
import blocks.Block;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;

import spirtes.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level.WideEasy class.
 * second level.
 */
public class Fall implements LevelInformation {

    /**
     * an empty constructor.
     */
    public Fall() {
    }

    /**
     * @return the num of balls in a specific level
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 10.0;

        velocities.add(Velocity.fromAngleAndSpeed(50.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(40.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(30.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(20.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(10.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-10.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-20.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-30.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-50.0, speed));

        return velocities;
    }

    /**
     * @return the speed of the paddle
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * @return the witdh of the paddle
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "level.Fall the leaves";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                FallBack fall = new FallBack();
                fall.drawOn(d);
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
        List<Block> blocks = new ArrayList<>();

        blocks.add(new Block(new Rectangle(new Point(25, 150), 50, 25, Color.red), 1));
        blocks.add(new Block(new Rectangle(new Point(75, 150), 50, 25, Color.red), 1));

        blocks.add(new Block(new Rectangle(new Point(125, 150), 50, 25, Color.orange), 1));
        blocks.add(new Block(new Rectangle(new Point(175, 150), 50, 25, Color.orange), 1));

        blocks.add(new Block(new Rectangle(new Point(225, 150), 50, 25, Color.red), 1));
        blocks.add(new Block(new Rectangle(new Point(275, 150), 50, 25, Color.red), 1));

        blocks.add(new Block(new Rectangle(new Point(325, 150), 50, 25, Color.yellow), 1));
        blocks.add(new Block(new Rectangle(new Point(375, 150), 50, 25, Color.yellow), 1));
        blocks.add(new Block(new Rectangle(new Point(425, 150), 50, 25, Color.yellow), 1));

        blocks.add(new Block(new Rectangle(new Point(475, 150), 50, 25, Color.orange), 1));
        blocks.add(new Block(new Rectangle(new Point(525, 150), 50, 25, Color.orange), 1));

        blocks.add(new Block(new Rectangle(new Point(575, 150), 50, 25, Color.yellow), 1));
        blocks.add(new Block(new Rectangle(new Point(625, 150), 50, 25, Color.yellow), 1));

        blocks.add(new Block(new Rectangle(new Point(675, 150), 50, 25, Color.red), 1));
        blocks.add(new Block(new Rectangle(new Point(725, 150), 50, 25, Color.red), 1));

        return blocks;

    }

    /**
     * @return Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * @return the color of the top block
     */
    public Color colorOfTheTop() {
        return Color.ORANGE;
    }
}

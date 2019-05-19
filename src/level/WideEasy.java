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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level.WideEasy class.
 * second level.
 */
public class WideEasy implements LevelInformation {

    /**
     * an empty constructor.
     */
    public WideEasy() {
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
        double speed = 5.0;

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
        return "Wide Easy";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#7EBBCA"));
                d.fillRectangle(0, 18, d.getWidth(), d.getHeight());

                d.setColor(Color.decode("#efe7b0"));
                d.fillCircle(150, 150, 60);

                int numRays = 100;
                int startX = 25;
                int endX = 775;

                for (int i = 1; i <= numRays; i++) {
                    d.drawLine(150, 150, (endX - startX) / numRays * i, 250);
                }

                d.setColor(Color.decode("#ecd749"));
                d.fillCircle(150, 150, 50);

                d.setColor(Color.decode("#ffe118"));
                d.fillCircle(150, 150, 40);
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

        blocks.add(new Block(new Rectangle(new Point(25, 250), 50, 25, Color.red), 1));
        blocks.add(new Block(new Rectangle(new Point(75, 250), 50, 25, Color.red), 1));

        blocks.add(new Block(new Rectangle(new Point(125, 250), 50, 25, Color.orange), 1));
        blocks.add(new Block(new Rectangle(new Point(175, 250), 50, 25, Color.orange), 1));

        blocks.add(new Block(new Rectangle(new Point(225, 250), 50, 25, Color.yellow), 1));
        blocks.add(new Block(new Rectangle(new Point(275, 250), 50, 25, Color.yellow), 1));

        blocks.add(new Block(new Rectangle(new Point(325, 250), 50, 25, Color.green), 1));
        blocks.add(new Block(new Rectangle(new Point(375, 250), 50, 25, Color.green), 1));
        blocks.add(new Block(new Rectangle(new Point(425, 250), 50, 25, Color.green), 1));

        blocks.add(new Block(new Rectangle(new Point(475, 250), 50, 25, Color.blue), 1));
        blocks.add(new Block(new Rectangle(new Point(525, 250), 50, 25, Color.blue), 1));

        blocks.add(new Block(new Rectangle(new Point(575, 250), 50, 25, Color.pink), 1));
        blocks.add(new Block(new Rectangle(new Point(625, 250), 50, 25, Color.pink), 1));

        blocks.add(new Block(new Rectangle(new Point(675, 250), 50, 25, Color.cyan), 1));
        blocks.add(new Block(new Rectangle(new Point(725, 250), 50, 25, Color.cyan), 1));

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
        return Color.BLUE;
    }
}

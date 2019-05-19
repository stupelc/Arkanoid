package level; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import background.WinterBack;
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
 * Green 3 class.
 * third level
 */
public class Winter implements LevelInformation {
    private List<Block> blocks;

    /**
     * a constructor.
     */
    public Winter() {

        blocks = new ArrayList<>();

        Color[] colors = {Color.decode("#990000"), Color.decode("#CC6600"), Color.decode("#ffff33")
                , Color.decode("#99ffff"), Color.decode("#ffffff")};
        int[] hitPoints = {2, 1, 1, 1, 1};


        int y = 100;
        int blockWidth = 25;
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                blocks.add(new Block(new Rectangle(new Point(j * 50 + 25, y), 50, 25, colors[i]), hitPoints[i]));
            }
            y += blockWidth;
        }
    }

    /**
     * @return the num of balls in a specific level
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    public List<Velocity> initialBallVelocities() {

        List<Velocity> velocities = new ArrayList<>();

        double speed = 10;

        velocities.add(Velocity.fromAngleAndSpeed(30.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-30.0, speed));

        return velocities;
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
        return 120;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "level.Winter time";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                WinterBack winter = new WinterBack();
                winter.drawOn(d);

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
        return this.blocks;
    }

    /**
     * @return Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * @return the color of the top paddle
     */
    public Color colorOfTheTop() {
        return Color.GRAY;
    }
}

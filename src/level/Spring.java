package level;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import background.SpringBack;
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
public class Spring implements LevelInformation {
    private List<Block> blocks;

    /**
     * an empty constructor.
     */
    public Spring() {
        blocks = new ArrayList<>();


        Color[] colors = {Color.GRAY, Color.decode("#99004c")
                , Color.YELLOW, Color.decode("#00994c"), Color.WHITE, Color.PINK, Color.CYAN};
        int[] hitPoints = {2, 1, 1, 1, 1, 1, 1};


        int y = 100;
        int blockWidth = 25;
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < 15; j++) {
                blocks.add(new Block(new Rectangle(new Point(j * 50 + 25, y), 50, 25, colors[i]), hitPoints[i]));
            }
            y += blockWidth;
        }

    }

    /**
     * @return the num of balls in a specific level
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * @return the velocity of all the balls
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        double speed = 5.0;

        velocities.add(Velocity.fromAngleAndSpeed(40.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(0.0D, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-40.0, speed));

        return velocities;
    }

    /**
     * @return the speed of the paddle
     */
    public int paddleSpeed() {
        return 8;
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
        return "level.Spring is coming";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                SpringBack spring = new SpringBack();
                spring.drawOn(d);
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
        return this.blocks.size();
    }

    /**
     * @return the color of the top block
     */
    public Color colorOfTheTop() {
        return Color.YELLOW;
    }

}



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
 * level.FinalFour class.
 * forth level
 */
public class FinalFour implements LevelInformation {

    private List<Block> blocks;

    /**
     * the constructor.
     */
    public FinalFour() {
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
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
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
        return "Final Four";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {

                d.setColor(Color.decode("#1788d0"));

                d.fillRectangle(0, 18, d.getWidth(), d.getHeight());

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(105 + i * 10, 400, 80 + i * 10, 600);
                }

                d.setColor(Color.decode("#cccccc"));
                d.fillCircle(100, 400, 23);
                d.fillCircle(120, 420, 27);
                d.setColor(Color.decode("#bbbbbb"));
                d.fillCircle(140, 390, 29);
                d.setColor(Color.decode("#aaaaaa"));
                d.fillCircle(160, 420, 22);
                d.fillCircle(180, 400, 32);


                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(605 + i * 10, 520, 580 + i * 10, 600);
                }

                d.setColor(Color.decode("#cccccc"));
                d.fillCircle(600, 500, 23);
                d.fillCircle(620, 540, 27);
                d.setColor(Color.decode("#bbbbbb"));
                d.fillCircle(640, 510, 29);
                d.setColor(Color.decode("#aaaaaa"));
                d.fillCircle(660, 530, 22);
                d.fillCircle(680, 520, 32);
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
     * @return the color of the top paddle
     */
    public Color colorOfTheTop() {
        return Color.GRAY;
    }
}

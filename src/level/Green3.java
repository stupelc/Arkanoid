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
 * Green 3 class.
 * third level
 */
public class Green3 implements LevelInformation {
    private List<Block> blocks;

    /**
     * a constructor.
     */
    public Green3() {

        blocks = new ArrayList<>();

        Color[] colors = {Color.decode("#003319"), Color.decode("#00994c"), Color.decode("#00cc66")
                , Color.decode("#66ff66"), Color.decode("#99ff99")};
        int[] hitPoints = {2, 1, 1, 1, 1};


        int y = 150;
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

        double speed = 5;

        velocities.add(Velocity.fromAngleAndSpeed(30.0, speed));
        velocities.add(Velocity.fromAngleAndSpeed(-30.0, speed));

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
        return 85;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * @return Returns a sprite with the background of the level
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#66b2ff"));
                d.fillRectangle(0, 18, d.getWidth(), d.getHeight());


                d.setColor(Color.decode("#4e4a49"));
                d.fillRectangle(110, 200, 10, 200);

                d.setColor(Color.decode("#d8ac66"));
                d.fillCircle(115, 200, 12);
                d.setColor(Color.decode("#f64d36"));
                d.fillCircle(115, 200, 8);
                d.setColor(Color.WHITE);
                d.fillCircle(115, 200, 3);

                d.setColor(Color.decode("#3e3a39"));
                d.fillRectangle(100, 400, 30, 200);

                d.setColor(Color.decode("#2e2a29"));
                d.fillRectangle(65, 450, 100, 200);

                int startX = 75;
                int startY = 460;

                d.setColor(Color.WHITE);
                for (int rows = 0; rows < 5; rows++) {
                    for (int columns = 0; columns < 5; columns++) {
                        d.fillRectangle(startX + columns * 18, startY + rows * 32, 10, 25);
                    }
                }
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

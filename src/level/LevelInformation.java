package level; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import blocks.Block;
import collision.Velocity;
import spirtes.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * the level.LevelInformation interface.
 * contains the information in each level.
 */
public interface LevelInformation {

    /**
     * @return the num of balls in a specific level
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the witdh of the paddle
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return the color of the top paddle
     */
    Color colorOfTheTop();
}
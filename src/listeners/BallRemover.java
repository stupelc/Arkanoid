package listeners; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import blocks.Block;
import game.GameLevel;
import geometry.Ball;
import utils.Counter;

/**
 * the listeners.BallRemover Class
 * a listeners.BallRemover is in charge of removing balls from the game in case the user isn't catch them.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private static final int HEIGHT = 600;

    /**
     * @param game        a game
     * @param removeBalls a counter that counts the balls number
     */
    public BallRemover(GameLevel game, Counter removeBalls) {
        this.game = game;
        this.remainingBalls = removeBalls;
    }

    /**
     * @param beingHit a block
     * @param hitter   a ball
     *                 This method is called whenever the ball hits the death region
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getOn().start().getY() == HEIGHT) {
            hitter.removeFromGame(this.game);
            //change the count of the balls in the main game
            this.game.getCounterBalls().decrease(1);
        }
    }
}

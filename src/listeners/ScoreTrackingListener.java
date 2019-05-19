package listeners; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import blocks.Block;
import geometry.Ball;
import utils.Counter;

/**
 * listeners.ScoreTrackingListener update a counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * @param scoreCounter a counter
     *                     constructor
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * @param beingHit a block
     * @param hitter   a ball
     *                 This method is called whenever the ball hits the death region
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCountHits() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }
}

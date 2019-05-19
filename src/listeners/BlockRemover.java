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
 * the listeners.BlockRemover Class
 * a listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * @param game          a game
     * @param removedBlocks the counter of the blocks that removed
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * @param beingHit a block
     * @param hitter   a ball
     *                 This method is called whenever the beingHit object is hit.
     *                 Blocks that are hit and reach 0 hit-points should be removed
     *                 from the game. Remember to remove this listener from the block
     *                 that is being removed from the game.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCountHits() == 1) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.setSum(this.remainingBlocks.getValue() - 1);
        }
        beingHit.setCountHits(beingHit.getCountHits() - 1);

    }
}
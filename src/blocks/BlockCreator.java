package blocks;

/**
 * the blocks.BlockCreator Class.
 */
public interface BlockCreator {
    /**
     * @param xpos the x position of a block
     * @param ypos the y position of a block
     * @return a block at the specified location.
     */
    Block create(int xpos, int ypos);

    /**
     * @return width of block.
     */
    int getWidth();

    /**
     * @return height of block.
     */
    int getHeight();
}
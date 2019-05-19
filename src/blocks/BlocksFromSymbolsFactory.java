package blocks;

import java.util.TreeMap;

/**
 * blocks.BlocksFromSymbolsFactory Class.
 */
public class BlocksFromSymbolsFactory {

    private TreeMap<String, Integer> spacerWidth;
    private TreeMap<String, BlockCreator> blockCreater;

    /**
     * Constructor.
     *
     * @param spacer map of spacers
     * @param block  map of blocks
     */
    public BlocksFromSymbolsFactory(TreeMap<String, Integer> spacer, TreeMap<String, BlockCreator> block) {
        this.spacerWidth = spacer;
        this.blockCreater = block;
    }

    /**
     * Checks if sybmol is space symbol.
     *
     * @param s symbol
     * @return true if it is
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidth.containsKey(s);
    }

    /**
     * Checks if symbol is block symbol.
     *
     * @param s symbol
     * @return true if it is
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreater.containsKey(s);
    }

    /**
     * Creates appropriate block.
     *
     * @param s    symbol
     * @param xpos x-position
     * @param ypos y-position
     * @return block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreater.get(s).create(xpos, ypos);
    }

    /**
     * Gets spacer width from map.
     *
     * @param s symbol
     * @return width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidth.get(s);
    }

    /**
     * Returns width of symbol.
     *
     * @param s symbol
     * @return width
     */
    public int getWidth(String s) {
        return this.blockCreater.get(s).getWidth();
    }

    /**
     * Returns height of symbol.
     *
     * @param s symbol
     * @return height
     */
    public int getHeight(String s) {
        return this.blockCreater.get(s).getHeight();
    }
}

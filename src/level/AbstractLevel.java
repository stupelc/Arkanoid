package level;

import blocks.Block;
import blocks.BlocksDefinitionReader;
import blocks.BlocksFromSymbolsFactory;
import collision.Velocity;
import spirtes.Fill;
import spirtes.ImageBackground;
import spirtes.SingleColorBackground;
import spirtes.Sprite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.awt.Color;

/**
 * Creates a level with the info from the file.
 */
public class AbstractLevel implements LevelInformation {

    private TreeMap<String, String> levelInfo;
    private LinkedList<String> blockStrings;
    private boolean levelOK;
    private int numOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Fill background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Constructor.
     * Checks that everything exists in the map, and gives everything a value.
     *
     * @param levelInfo    map with level information
     * @param blockStrings blockStrings
     */
    public AbstractLevel(TreeMap<String, String> levelInfo, LinkedList<String> blockStrings) {
        this.levelInfo = levelInfo;
        this.blockStrings = blockStrings;
        //checks that we get all the information we need
        everythingExists();
        if (this.levelOK) {
            fillingTheVoid();
        }
    }

    /**
     * Gives everything a value.
     */
    private void fillingTheVoid() {

        this.levelName = this.levelInfo.get("level_name");
        setBallInfo();
        this.paddleSpeed = Integer.parseInt(this.levelInfo.get("paddle_speed"));
        this.paddleWidth = Integer.parseInt(this.levelInfo.get("paddle_width"));
        this.numberOfBlocksToRemove = Integer.parseInt(this.levelInfo.get("num_blocks"));
        setBackground();
        setBlocks();

    }

    /**
     * sets background.
     */
    private void setBackground() {
        String backGround = this.levelInfo.get("background");
        //trims string
        String bgWithoutParentheses = backGround.substring(backGround.indexOf("(") + 1, backGround.indexOf(")"));
        if (backGround.startsWith("color")) {
            this.background = new SingleColorBackground(bgWithoutParentheses);
        } else {
            this.background = new ImageBackground(bgWithoutParentheses);
        }
    }

    /**
     * Sets blocks.
     */
    private void setBlocks() {

        this.blocks = new LinkedList<Block>();
        String oneBlock = null;

        //opens the blocks definition file and creates a factory
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                this.levelInfo.get("block_definitions"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromreader(reader);

        //gets the blocks starting position
        int xposKeeper;
        int xpos = Integer.parseInt(this.levelInfo.get("blocks_start_x"));
        xposKeeper = xpos;
        int ypos = Integer.parseInt(this.levelInfo.get("blocks_start_y"));

        //goes through all the lines and adds to list of blocks
        for (String currentLine : this.blockStrings) {
            //Checks is there is only space symbol on line,
            //moves down row
            if (currentLine.length() == 1 && factory.isSpaceSymbol(currentLine)) {
                ypos += factory.getSpaceWidth(currentLine);
                continue;
            }
            //Goes through line making blocks and spaces
            for (int i = 0; i < currentLine.length(); i++) {
                oneBlock = currentLine.substring(i, i + 1);
                if (factory.isBlockSymbol(oneBlock)) {
                    Block newBlock = factory.getBlock(oneBlock, xpos, ypos);
                    this.blocks.add(newBlock);
                    xpos += factory.getWidth(oneBlock);
                } else if (factory.isSpaceSymbol(oneBlock)) {
                    xpos += factory.getSpaceWidth(oneBlock);
                }
            }
            //After finished all blocks on line goes down a row
            ypos += Integer.parseInt(levelInfo.get("row_height"));
            xpos = xposKeeper;
        }
    }

    /**
     * Gives numOfBalls and ballVelocities a value.
     */
    private void setBallInfo() {

        this.initialBallVelocities = new LinkedList<Velocity>();
        //creates array of the different velocities
        String[] velocities = this.levelInfo.get("ball_velocities").split(" ");

        //makes sure the level is ok this far
        if (!this.levelOK) {
            return;
        }

        //goes through array of velocities
        for (String currentVelocity : velocities) {
            //checks if it contains a comma, if not: returns error
            if (!currentVelocity.contains(",")) {
                this.levelOK = false;
                return;
            }
            //splits by comma and changes to int
            String[] angleAndSpeed = currentVelocity.split(",");
            int angle = Integer.parseInt(angleAndSpeed[0]);
            int speed = Integer.parseInt(angleAndSpeed[1]);
            //creates new velocity and adds it to the list of velocities
            Velocity newVelocity = Velocity.fromAngleAndSpeed(angle, speed);
            this.initialBallVelocities.add(newVelocity);
        }
        //sets numberOfBalls to the size of array of velocities
        this.numOfBalls = velocities.length;
    }

    /**
     * Checks that everything except for blocks info is in the map.
     */
    private void everythingExists() {
        if (levelInfo.containsKey("level_name")
                && levelInfo.containsKey("ball_velocities")
                && levelInfo.containsKey("background")
                && levelInfo.containsKey("paddle_speed")
                && levelInfo.containsKey("paddle_width")
                && levelInfo.containsKey("block_definitions")
                && levelInfo.containsKey("blocks_start_x")
                && levelInfo.containsKey("blocks_start_y")
                && levelInfo.containsKey("row_height")
                && levelInfo.containsKey("num_blocks")) {
            this.levelOK = true;
        } else {
            this.levelOK = false;
        }
    }

    /**
     * Number of balls in a game.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * Speed of the paddle.
     *
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Width of the paddle.
     *
     * @return paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite containing the background of the level.
     *
     * @return a sprite containing the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of the blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size()
     *
     * @return number of blocks to be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * @return true if creating the level worked.
     */
    public boolean isOK() {
        return this.levelOK;
    }

    /**
     * @return the color on the sides of the frame
     */
    public Color colorOfTheTop() {
        return Color.GRAY;
    }
}

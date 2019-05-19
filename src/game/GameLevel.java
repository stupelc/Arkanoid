package game;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import animation.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import blocks.Block;
import collision.Collidable;
import collision.SpriteCollection;
import collision.Velocity;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import indincators.LivesIndicator;
import indincators.NameIndicator;
import indincators.ScoreIndicator;
import level.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import spirtes.Paddle;
import spirtes.Sprite;
import utils.Counter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * game.GameLevel class
 * with the characteristics sprite (a sprite collection) and environment (a game environment) and a gui
 * include the constructors addSprite, notifyAllTimePassed, drawAllOn, initialize (create the game) and run.
 */

public class GameLevel implements Animation {
    private GUI gui;
    private Counter counterBalls;
    private Counter score;

    private List<Velocity> velocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Counter counterBlocks;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private Sprite backRound;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover listenerBlock;
    private BallRemover listenerBall;
    private ScoreTrackingListener listenerScore;
    private LevelInformation levelInformation;

    private Counter lives;

    private AnimationRunner runner;
    private boolean running;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int HEIGHT_BLOCK = 20;
    private static final int BALL_RADIUS = 5;


    /**
     * @param levelInfo       the information of the game
     * @param keyboardSensor  teh keyboard sensor
     * @param animationRunner the animation we are gonna run
     * @param gui             the gui we are gonna use
     * @param countLives      the num of lives we had for the game
     * @param countScore      the count score we have for the current game
     */
    public GameLevel(LevelInformation levelInfo, biuoop.KeyboardSensor keyboardSensor
            , AnimationRunner animationRunner, GUI gui, Counter countLives, Counter countScore) {
        //create the gui for the game
        this.gui = gui;
        this.levelInformation = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;

        //update the counters of score and lives
        this.score = countScore;
        this.lives = countLives;
    }


    /**
     * initialize the game.
     */
    public void initialize() {


        //initialize the game enviroment
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();

        //initialize the parameters from the levelInformation variable
        this.counterBalls = new Counter(levelInformation.numberOfBalls());
        this.velocities = levelInformation.initialBallVelocities();

        //spirtes.Paddle
        this.paddleSpeed = levelInformation.paddleSpeed();
        this.paddleWidth = levelInformation.paddleWidth();

        //NameLevel
        this.levelName = levelInformation.levelName();

        this.counterBlocks = new Counter(levelInformation.numberOfBlocksToRemove());

        //spirtes.BackRound
        this.backRound = levelInformation.getBackground();

        //initialize the listeners
        this.listenerBlock = new BlockRemover(this, this.counterBlocks);
        this.listenerBall = new BallRemover(this, this.counterBalls);
        this.listenerScore = new ScoreTrackingListener(this.score);

        this.addSprite(this.backRound);
        this.addGameBlocksToTheGame();

        Block leftBorder =
                new Block(new Rectangle(new Point(0, 0), 20, HEIGHT, levelInformation.colorOfTheTop()), -1);
        Block topBorder =
                new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT_BLOCK, levelInformation.colorOfTheTop()), -1);
        Block rightBorder =
                new Block(new Rectangle(new Point(WIDTH - 20, 0), 20, HEIGHT, levelInformation.colorOfTheTop()), -1);
        Block dieRegion =
                new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, HEIGHT_BLOCK, Color.GRAY), -1);

        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        topBorder.addToGame(this);
        dieRegion.addToGame(this);

        Rectangle upzone = topBorder.getCollisionRectangle();
        LivesIndicator upLifeZone = new LivesIndicator(upzone, this.lives);
        this.addSprite(upLifeZone);

        NameIndicator upNameZone = new NameIndicator(upzone, this.levelName);
        this.addSprite(upNameZone);

        ScoreIndicator upScoreZone = new ScoreIndicator(upzone, this.score);
        this.addSprite(upScoreZone);

        dieRegion.addToGame(this);
        dieRegion.addHitListener(this.listenerBall);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        //we steel want the gae run
        this.running = true;

        //create the paddle and the balls
        //if this is the first time
        if (this.paddle == null) {
            this.paddle = new Paddle(new Rectangle(new Point(WIDTH / 2 - (this.paddleWidth / 2)
                    , HEIGHT - 40), this.paddleWidth
                    , HEIGHT_BLOCK - 10, Color.ORANGE)
                    , new Line(new Point(20, 560), new Point(780, 560))
                    , this.paddleSpeed);
        } else {
            //if it isn't the first time we just want to set the location

            this.paddle.removeFromGame(this);
            this.paddle = new Paddle(new Rectangle(new Point(WIDTH / 2 - (this.paddleWidth / 2)
                    , HEIGHT - 40), this.paddleWidth
                    , HEIGHT_BLOCK - 10, Color.ORANGE)
                    , new Line(new Point(20, 560), new Point(780, 560))
                    , this.paddleSpeed);
        }
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);

        //create the balls on the top of the paddle
        this.counterBalls = new Counter(this.levelInformation.numberOfBalls());
        this.createBallsOnTopOfPaddle();

        //this.runner = new animation.AnimationRunner(this.gui);
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.runner.run(this);
    }

    /**
     * @return the if the ball should stop
     */
    public boolean shouldStop() {
        if (this.counterBlocks.getValue() == 0 || this.counterBalls.getValue() == 0) {
            if (this.counterBlocks.getValue() == 0) {
                this.listenerScore.getCurrentScore().increase(100);
            }
        } else {
            return !this.running;
        }

        return true;
    }

    /**
     * create balls on the top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        int xPosition = (int) (this.paddle.getPaddle().getUpperLeft().getX() + this.paddle.getPaddle().getWidth() / 2);
        int yPosition = (int) this.paddle.getPaddle().getUpperLeft().getY() - 10;

        //make the num balls according to the level
        for (int i = 0; i < this.counterBalls.getValue(); i++) {
            this.createBall(xPosition, yPosition, this.velocities.get(i));
        }
    }

    /**
     * @param d  a drawSurface a drawSurface
     * @param dt dt
     */
    public void doOneFrame(DrawSurface d, double dt) {

        this.backRound.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        if (this.keyboard.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen(this.keyboard)));
        }

        if (counterBalls.getValue() <= 0) {
            this.running = false;
            this.lives.decrease(1);
            return;
        }
    }


    /**
     * add the level blocks to the game.
     */
    public void addGameBlocksToTheGame() {
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block gameBlock = this.levelInformation.blocks().get(i);
            gameBlock.addToGame(this);
            gameBlock.addHitListener(this.listenerBlock);
            gameBlock.addHitListener(this.listenerScore);
        }
    }

    /**
     * @param x        the x position
     * @param y        the y position
     * @param velocity the current velocity
     */
    public void createBall(int x, int y, Velocity velocity) {

        Point centerPoint = new Point(x, y);
        Ball ball1 = new Ball(centerPoint, BALL_RADIUS, Color.BLACK);
        ball1.setVelocity(velocity.getDx(), velocity.getDy());
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);

    }

    /**
     * add a colliadable to the game.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.getList().add(c);
    }

    /**
     * add a sprite to the game.
     *
     * @param s a sprite to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.getList().add(s);
    }

    /**
     * @param c a specific collision.Collidable
     *          remove a collision.Collidable from the game
     */
    public void removeCollidable(Collidable c) {
        this.environment.getList().remove(c);
    }

    /**
     * @param s a specific sprite
     *          remove a spirtes.Sprite from the sprite's list
     */
    public void removeSprite(Sprite s) {
        this.sprites.getList().remove(s);
    }

    /**
     * @return the num of the current balls
     */
    public Counter getCounterBalls() {
        return counterBalls;
    }

    /**
     * @return the cuurent value of the blocks counter
     */
    public Counter getCounterBlocks() {
        return counterBlocks;
    }

    /**
     * @return how many lives there are in the game
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * @return the current score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * @return the spriteCollection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return the environment of the game
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

}
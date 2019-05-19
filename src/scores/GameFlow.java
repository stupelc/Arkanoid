package scores;
/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

import animation.AnimationRunner;
import animation.EndScreem;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import level.LevelInformation;
import menu.MenuRun;
import utils.Counter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * scores.GameFlow class.
 * run all the game
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private HighScoresAnimation highScoresAnimation;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private DialogManager dialogManager;
    private HighScoresTable table;
    private GameLevel level;
    private File file;
    private List<LevelInformation> infoLevels;
    private MenuRun menu;

    /**
     * @param ar            the animation runner
     * @param ks            keyboard
     * @param gui           the gui we are gonna use
     * @param dialogManager dialogManager
     * @param menuRun       the menuRun
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, DialogManager dialogManager, MenuRun menuRun) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.dialogManager = this.gui.getDialogManager();
        this.table = new HighScoresTable(5);
        this.file = new File("src/main/highscores.txt");
        this.menu = menuRun;

        try {
            this.table.load(file);
        } catch (IOException e) {
            System.err.println("Failed loading high score table");
            e.printStackTrace(System.err);
            return;
        }
    }

    /**
     * @param levels a list of the levels information
     * @param with   'true' if there are args 'false' otherwise
     * @param args   the args
     *               run the levels in the game
     */
    public void runLevels(List<LevelInformation> levels, boolean with, String[] args) {
        Counter countLives = new Counter(1);
        Counter countScore = new Counter(0);

        //the first time we run the game
        infoLevels = levels;


        for (LevelInformation levelInfo : infoLevels) {

            this.level = new GameLevel(levelInfo, this.keyboardSensor
                    , this.animationRunner, this.gui, countLives, countScore);

            //level.clearBlocks();
            level.initialize();

            while (level.getLives().getValue() > 0 && level.getCounterBlocks().getValue() > 0) {
                level.playOneTurn();
            }

            if (level.getLives().getValue() <= 0) {
                animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new EndScreem(countScore.getValue(), this.keyboardSensor, false)));

                if (this.table.getListScores().size() < this.table.getSizeTable()
                        || this.table.getHighScores().get(4).getScore() < level.getScore().getValue()) {
                    String name = this.dialogManager.showQuestionDialog("Name", "What is your name?",
                            "Anonymous");
                    System.out.println(name);
                    this.table.add(new ScoreInfo(name, level.getScore().getValue()));
                    try {
                        this.table.save(file);
                    } catch (IOException e) {
                        System.err.println("Failed loading high score table");
                        e.printStackTrace(System.err);
                        return;
                    }
                }
                this.highScoresAnimation = new HighScoresAnimation(this.table, "space", this.keyboardSensor);
                animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        this.highScoresAnimation));

                this.menu.runTheMenu(with, args);
            }
        }
        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreem(countScore.getValue(), this.keyboardSensor, true)));

        if (this.table.getHighScores().get(4).getScore() < level.getScore().getValue()) {
            String name = this.dialogManager.showQuestionDialog("Name", "What is your name?", "Anonymous");
            System.out.println(name);
            this.table.add(new ScoreInfo(name, level.getScore().getValue()));
            try {
                this.table.save(file);

                this.highScoresAnimation = new HighScoresAnimation(this.table, "space", this.keyboardSensor);
                animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, "space", this.highScoresAnimation));

                this.menu.runTheMenu(with, args);

            } catch (IOException e) {
                System.err.println("Failed loading high score table");
                e.printStackTrace(System.err);
                return;
            }
        }
    }

    /**
     * @return the table that we use
     */
    public HighScoresTable getTable() {
        return table;
    }
}
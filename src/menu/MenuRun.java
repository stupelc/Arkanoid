package menu;

import animation.Animation;
import animation.AnimationRunner;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import level.LevelInformation;
import level.LevelSpecificationReader;

import scores.GameFlow;
import scores.HighScoresAnimation;

import java.io.LineNumberReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * the menu.MenuRun class.
 */
public class MenuRun {

    private List<LevelInformation> levels;
    private GUI gui;
    private AnimationRunner animationRunner;
    private DialogManager dialogManager;
    private KeyboardSensor keyboard;

    /**
     * the main constructor that runs only in the first time.
     */
    public MenuRun() {
        this.gui = new GUI("check", 800, 600);
        this.animationRunner = new AnimationRunner(gui);
        this.dialogManager = gui.getDialogManager();
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * @param with 'true' - if there are args. 'false' - if there aren't args
     * @param args the array of the levels
     *             the restart function that runs everytime that we start a game
     */
    public void runTheMenu(boolean with, String[] args) {
        GameFlow game = new GameFlow(animationRunner, keyboard, gui, dialogManager, this);

//        List<LevelInformation> list;
//        if (with) {
//            list = withArgs(args);
//        } else {
//            list = withoutArgs();
//        }
//
//        this.levels = list;


        //initialize the game
        while (true) {
            Animation scores = new HighScoresAnimation(game.getTable(), "space", keyboard);

            String title = "Arkanoid";
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, title, null);

            Task<Void> highScores = new Task<Void>() {
                @Override
                public Void run() {
                    animationRunner.run(scores);
                    return null;
                }
            };

            Task<Void> start = new Task<Void>() {
                public Void run() {
                    Menu<Task<Void>> sunMenu = new SubMenu<Task<Void>>(keyboard, "OPTIONS", null);
                    runTheSubMenu(args, game);
                    return null;
                }
            };

            Task<Void> exit = new Task<Void>() {
                public Void run() {
                    animationRunner.getGui().close();
                    System.exit(0);
                    return null;
                }
            };

            menu.addSelection("p", "Play Game", start);
            menu.addSelection("h", "High Scores", highScores);
            menu.addSelection("e", "Exit", exit);

            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * run the sub menu.
     *
     * @param args the string args
     * @param game the game
     */
    public void runTheSubMenu(String[] args, GameFlow game) {
        LevelSpecificationReader levelsReader = new LevelSpecificationReader();
        Map<String, String> levelOptions = new TreeMap<>();
        Map<String, String> levelPaths = new TreeMap<>();
        BufferedReader levelSetsFile = null;
        try {
            levelSetsFile = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream(args[0])));
        } catch (Exception e) {
            InputStream is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("definitions/level_definitions.txt");
            levelSetsFile = new BufferedReader(
                    new InputStreamReader(is));
        }
        try {
            LineNumberReader lineReader = new LineNumberReader(levelSetsFile);
            String line = lineReader.readLine();
            String c = "c";
            String levelName;
            while (line != null) {
                if (lineReader.getLineNumber() % 2 == 1) {
                    String[] parts = line.split(":");
                    c = parts[0];
                    levelName = parts[1];
                    levelOptions.put(c, levelName);
                } else {
                    levelPaths.put(c, line);
                }
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String title = "Level Options";
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, title, null);

            Task<Void> easy = new Task<Void>() {
                @Override
                public Void run() {
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelPaths.get("e"));
                        List<LevelInformation> easyLevels =
                                levelsReader.fromReader(new BufferedReader(new InputStreamReader(is)));
                        game.runLevels(easyLevels, true, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

            Task<Void> hard = new Task<Void>() {
                public Void run() {
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelPaths.get("h"));
                        List<LevelInformation> hardLevels =
                                levelsReader.fromReader(new BufferedReader(new InputStreamReader(is)));
                        game.runLevels(hardLevels, true, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

            Task<Void> bonus = new Task<Void>() {
                public Void run() {
                    try {
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelPaths.get("b"));
                        List<LevelInformation> bonusLevels =
                                levelsReader.fromReader(new BufferedReader(new InputStreamReader(is)));
                        game.runLevels(bonusLevels, true, args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };

            menu.addSelection("e", "Easy", easy);
            menu.addSelection("h", "Hard", hard);
            menu.addSelection("b", "Bonus", bonus);

            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }


    /**
     * in case we don't have args in the declaration.
     *
     * @return a list of levelInformations.
     */
    public List<LevelInformation> withoutArgs() {
        BufferedReader levelSetsFile = null;

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                "definitions/level_definitions.txt");
        levelSetsFile = new BufferedReader(new InputStreamReader(is));

        LevelSpecificationReader reader = new LevelSpecificationReader();
        List<LevelInformation> l = reader.fromReader(levelSetsFile);
        return l;
    }

    /**
     * @param args the args array
     * @return a list of the levels information we are gonna to run
     */
    public List<LevelInformation> withArgs(String[] args) {

        BufferedReader levelSetsFile = null;

        try {
            levelSetsFile = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().
                    getResourceAsStream(args[0])));
        } catch (Exception e) {
            InputStream is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("definitions/level_definitions.txt");
            levelSetsFile = new BufferedReader(
                    new InputStreamReader(is));
        }

        LevelSpecificationReader reader = new LevelSpecificationReader();
        List<LevelInformation> l = reader.fromReader(levelSetsFile);
        return l;
    }
}

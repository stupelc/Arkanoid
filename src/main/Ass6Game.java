package main;

import menu.MenuRun;

/**
 * Ass6Game Game.
 */
public class Ass6Game {

    /**
     * main that creates, initializes and runs the game.
     *
     * @param args name of file to level sets.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            MenuRun menuRun = new MenuRun();
            menuRun.runTheMenu(false, args);
        } else {
            MenuRun menuRun = new MenuRun();
            menuRun.runTheMenu(true, args);
        }
    }
}


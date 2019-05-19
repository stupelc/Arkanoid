package menu;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;

import java.util.List;
import java.awt.Color;


/**
 * the menu.MenuAnimation Class.
 *
 * @param <T> a generics variable.
 */
public class SubMenu<T> implements Menu<T> {
    private KeyboardSensor keyboardSensor;
    private String title;
    private T status;
    private List<Selection> listSelection;

    /**
     * @param keyboardSensor the keyboard sensor
     * @param title          the title of the key that was pressed
     * @param status         the object that we wanna to open according to the key that pressed
     *                       the constructor
     */
    public SubMenu(KeyboardSensor keyboardSensor, String title, T status) {
        this.status = status;
        this.keyboardSensor = keyboardSensor;
        this.title = title;
        this.listSelection = new ArrayList<>();
    }

    /**
     * @param key       the key we pressed
     * @param message   the message should print according the key that pressed
     * @param returnVal the object that should opened according the key that pressed
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.listSelection.add(new Selection(key, message, returnVal));
    }

    /**
     * @param key     the key we pressed
     * @param message the message that will be write
     * @param subMenu the subMenu class
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    /**
     * @return the status of the game
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * @param d  the drawSurface
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.BLACK);
        d.drawText(51, 50, title, 32);
        d.drawText(49, 50, title, 32);
        d.drawText(50, 51, title, 32);
        d.drawText(50, 49, title, 32);
        d.setColor(Color.YELLOW);
        d.drawText(50, 50, title, 32);

        for (int i = 0; i < listSelection.size(); i++) {
            int optionX = 100;
            int optionY = 120 + i * 40;
            String optionText = "(" + listSelection.get(i).getKey() + ") " + listSelection.get(i).getMessage();

            d.setColor(Color.BLACK);
            d.drawText(optionX + 1, optionY, optionText, 24);
            d.drawText(optionX - 1, optionY, optionText, 24);
            d.drawText(optionX, optionY + 1, optionText, 24);
            d.drawText(optionX, optionY - 1, optionText, 24);

            d.setColor(Color.RED);
            d.drawText(optionX, optionY, optionText, 24);

        }

        for (int i = 0; i < listSelection.size(); i++) {
            if (keyboardSensor.isPressed(listSelection.get(i).getKey())) {
                this.status = (T) listSelection.get(i).getReturnVal();
            }
        }
    }

    /**
     * @return if the game should stop or not
     */
    @Override
    public boolean shouldStop() {
        if (this.status != null) {
            return true;
        }
        return false;
    }
}
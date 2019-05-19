package menu;

import animation.Animation;

/**
 * the menu.Menu interface.
 *
 * @param <T> a generic variable
 */
public interface Menu<T> extends Animation {
    /**
     * @param key       the key we pressed
     * @param message   the message should print according the key that pressed
     * @param returnVal the object that should opened according the key that pressed
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status
     */
    T getStatus();

    /**
     * @param key     the key we pressed
     * @param message the message that will be write
     * @param subMenu the subMenu class
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
package menu;

/**
 * the menu.Task interface.
 *
 * @param <T> the generic variable
 */
public interface Task<T> {
    T run();
}
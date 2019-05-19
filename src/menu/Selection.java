package menu;

/**
 * @param <T> a generics vairiable
 *            the menu.Selection Class
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * @param key       the key that pressed
     * @param message   the message will print when we pressed the key
     * @param returnVal the object that will open when the key will press
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @return the key that was pressed
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the message that prints when the key is pressed
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the object that open when a kee is pressed
     */
    public T getReturnVal() {
        return returnVal;
    }
}

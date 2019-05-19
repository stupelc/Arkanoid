package listeners; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

/**
 * the listeners.HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * @param hl a hit listener
     *           Add hl as a listener to hit events.
     */
    void addHitListener(HitListener hl);
}
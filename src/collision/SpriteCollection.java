package collision;

import biuoop.DrawSurface;
import spirtes.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

/**
 * collision.SpriteCollection class
 * with the characteristic list of sprites
 * include the constructors addSprite, notifyAllTimePassed, drawAllOn.
 */
public class SpriteCollection {
    private List<Sprite> list = new ArrayList<>();

    /**
     * @return the sprite list
     */
    public List<Sprite> getList() {
        return list;
    }

    /**
     * add a sprite to the list.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        int index = this.list.size() - 1;
        if (list.size() == 0) {
            index = 0;
        }
        this.list.add(index, s);
    }

    /**
     * @param dt dt
     *           call timePassed() on all sprites.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d a drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).drawOn(d);
        }
    }
}
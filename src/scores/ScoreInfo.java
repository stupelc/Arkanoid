package scores;

import java.io.Serializable;

/**
 * Score Info Class.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * @param name  the name player
     * @param score the score of the player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }
}

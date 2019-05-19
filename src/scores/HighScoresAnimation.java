package scores;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;

/**
 * the scores.HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable highScoreTable;
    private String endKey;
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * @param highScoreTable the table we wanna to show
     * @param endKey         the bottom we wanna to press to finish the screen show
     * @param keyboardSensor the keyboardSensor
     *                       prints the table to the screen
     */
    public HighScoresAnimation(HighScoresTable highScoreTable, String endKey, KeyboardSensor keyboardSensor) {
        this.highScoreTable = highScoreTable;
        this.endKey = endKey;
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
    }

    /**
     * @param ds the drawSurface
     * @param dt dt
     */
    public void doOneFrame(DrawSurface ds, double dt) {
        ds.setColor(Color.LIGHT_GRAY);
        ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());


        ds.setColor(Color.BLACK);
        ds.drawText(51, 50, "High Scores", 32);
        ds.drawText(49, 50, "High Scores", 32);
        ds.drawText(50, 51, "High Scores", 32);
        ds.drawText(50, 49, "High Scores", 32);
        ds.setColor(Color.YELLOW);
        ds.drawText(50, 50, "High Scores", 32);

        ds.setColor(Color.BLACK);
        ds.drawText(101, 120, "Player Name", 24);
        ds.drawText(99, 120, "Player Name", 24);
        ds.drawText(100, 121, "Player Name", 24);
        ds.drawText(100, 119, "Player Name", 24);
        ds.setColor(Color.GREEN);
        ds.drawText(100, 120, "Player Name", 24);

        ds.setColor(Color.BLACK);
        ds.drawText(451, 120, "Score", 24);
        ds.drawText(449, 120, "Score", 24);
        ds.drawText(450, 121, "Score", 24);
        ds.drawText(450, 119, "Score", 24);
        ds.setColor(Color.GREEN);
        ds.drawText(450, 120, "Score", 24);

        ds.setColor(Color.BLACK);
        ds.drawLine(100, 130, 700, 130);
        ds.setColor(Color.GREEN);
        ds.drawLine(100, 131, 700, 131);
        ds.setColor(Color.BLACK);
        ds.drawLine(100, 132, 700, 132);


        ds.setColor(Color.BLACK);
        for (int i = 0; i < highScoreTable.getListScores().size(); i++) {
            ScoreInfo player = highScoreTable.getListScores().get(i);
            int nameX = 100;
            int scoreX = 450;
            int entryY = 170 + i * 40;

            ds.setColor(Color.BLACK);
            ds.drawText(nameX + 1, entryY, player.getName(), 24);
            ds.drawText(nameX - 1, entryY, player.getName(), 24);
            ds.drawText(nameX, entryY + 1, player.getName(), 24);
            ds.drawText(nameX, entryY - 1, player.getName(), 24);
            ds.setColor(Color.ORANGE);
            ds.drawText(nameX, entryY, player.getName(), 24);


            ds.setColor(Color.BLACK);
            ds.drawText(scoreX + 1, entryY, "" + player.getScore(), 24);
            ds.drawText(scoreX - 1, entryY, "" + player.getScore(), 24);
            ds.drawText(scoreX, entryY + 1, "" + player.getScore(), 24);
            ds.drawText(scoreX, entryY - 1, "" + player.getScore(), 24);

            ds.setColor(Color.ORANGE);
            ds.drawText(scoreX, entryY, "" + player.getScore(), 24);
        }

        String msg = "Press space to continue";
        ds.setColor(Color.BLACK);
        ds.drawText(199, 500, msg, 32);
        ds.setColor(Color.decode("#1788d0"));
        ds.drawText(200, 501, msg, 32);
        ds.setColor(Color.BLACK);
        ds.drawText(202, 502, msg, 32);

        if (this.keyboardSensor.isPressed("space")) {
            this.stop = true;
        }
    }

    /**
     * @return true if the game should stop and false if the game should stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

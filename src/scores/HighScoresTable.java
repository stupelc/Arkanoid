package scores;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


/**
 * the HighScoreTable Class.
 */
class HighScoresTable implements Serializable {
    private List<ScoreInfo> listScores;
    private int sizeTable;

    /**
     * @param size the size of the table
     *             Create an empty high-scores table with the specified size.
     *             The size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.sizeTable = size;
        this.listScores = new ArrayList<>();
    }

    /**
     * @param score Add a high-score.
     */
    public void add(ScoreInfo score) {
        //get the place we want enter to
        int rank = this.getRank(score.getScore());
        //when we don't want to enter the score att all
        if (rank == -1) {
            return;
            // 1/another place : entering the score to the table
        } else {
            listScores.add(rank - 1, score);
        }

        //if we had an extra score on the list we want to remove it because we want only 'size' scores
        if (listScores.size() > sizeTable) {
            listScores.remove(listScores.get(listScores.size() - 1));
        }
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.sizeTable;
    }

    /**
     * @return Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.listScores;
    }

    /**
     * @param score the score of the specific player
     * @return return the rank of the current score: where will it
     * // be on the list if added?
     * // Rank 1 means the score will be highest on the list.
     * // Rank `size` means the score will be lowest.
     * // Rank > `size` means the score is too low and will not
     * //      be added to the list.
     */
    public int getRank(int score) {
        ///the first time we enter to the table
        if (listScores.size() == 0) {
            return 1;
        }
        // a regular entering
        for (int i = 0; i < listScores.size(); i++) {
            if (score > listScores.get(i).getScore()) {
                return i + 1;
            }
        }
        //if we cant add the score because there isn't more space
        if (sizeTable == listScores.size()) {
            return -1;
        }
        //if we add the score in the last place
        return listScores.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.getHighScores().clear();
    }

    /**
     * @param filename the name of the file
     * @throws IOException an exception
     *                     load table data from file
     */
    public void load(File filename) throws IOException {
        if (filename.exists()) {
            if (loadFromFile(filename) != null) {
                this.listScores = loadFromFile(filename).getHighScores();
            } else {
                throw new IOException("Failed reading file");
            }
        }
    }

    /**
     * @param fileName the name of the file
     * @throws IOException an exception
     *                     Save table data to the specified file.
     */
    public void save(File fileName) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + fileName);
            }
        }
    }

    /**
     * @param filename the file we want to load from
     * @return Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {

        HighScoresTable highScoresTable = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename));
            // unsafe down casting, we better be sure that the stream really contains a Person!
            highScoresTable = (HighScoresTable) objectInputStream.readObject();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return null;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return null;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return null;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }

        return highScoresTable;
    }


    /**
     * @return the list of the scores
     */
    public List<ScoreInfo> getListScores() {
        return listScores;
    }

    /**
     * @return the size of the current table
     */
    public int getSizeTable() {
        return sizeTable;
    }
}

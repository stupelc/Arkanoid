package level;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * level.LevelSpecificationReader Class.
 */
public class LevelSpecificationReader {

    //list of all the levels that we had in the game
    private List<LevelInformation> levels;
    //a boolean variable that checks if the input is OK
    private boolean infoOK;

    /**
     * Constructor.
     */
    public LevelSpecificationReader() {
        this.levels = new LinkedList<LevelInformation>();
        this.infoOK = true;
    }

    /**
     * Gets list of levels from file.
     *
     * @param reader to file of level info
     * @return list of levels
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        //casts to buffer reader
        BufferedReader levelReader = (BufferedReader) reader;

        //makes all the levels
        try {
            String s;
            //reads through the file
            while (levelReader.ready()) {

                //read one line from the file
                s = levelReader.readLine();

                //creates new level and adds to list
                if (s.equals("START_LEVEL")) {

                    //we creates a map of level info and a block info
                    TreeMap<String, String> levelInfo = gatherLevelInfo(levelReader);

                    //puts all the information about blocks into a linked list
                    LinkedList<String> blocksInfo = gatherBlocksInfo(levelReader);

                    //create a level information according to level information and the blocks information
                    LevelInformation newLevel = new AbstractLevel(levelInfo, blocksInfo);

                    //checks if the input from the file is ok
                    if (infoOK) {
                        levels.add(newLevel);
                    }
                }
            }
            //reader.close();
        } catch (IOException e) {
            System.out.println(
                    "cannot read levels definition file");
            e.printStackTrace();
        }
        return this.levels;
    }

    /**
     * Puts the level info into a map, stops at blocks info part.
     *
     * @param reader place in file to the level info
     * @return map with info
     */
    public TreeMap<String, String> gatherLevelInfo(BufferedReader reader) {

        TreeMap<String, String> map = new TreeMap<String, String>();
        String s;

        try {
            while (reader.ready()) {
                s = reader.readLine();
                if (s.equals("START_BLOCKS")) {
                    break;
                }
                if (!s.contains(":")) {
                    this.infoOK = false;
                    return null;
                }
                //separate the key and value from the info about the levels and put them into a map
                String[] keyAndValue = s.split(":");
                map.put(keyAndValue[0], keyAndValue[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return the map of the levels info witch contains the key and value of the information
        return map;
    }

    /**
     * Puts the blocks info into a list, stops at blocks_end.
     *
     * @param reader place of blocks info
     * @return list of strings with block info
     */
    public LinkedList<String> gatherBlocksInfo(BufferedReader reader) {

        String s;
        LinkedList<String> blocksInfo = new LinkedList<String>();

        try {
            while (reader.ready()) {
                s = reader.readLine();
                if (s.equals("END_BLOCKS")) {
                    break;
                }
                blocksInfo.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return blocksInfo;
    }
}
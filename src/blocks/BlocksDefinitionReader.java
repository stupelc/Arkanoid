package blocks;

import spirtes.Fill;
import spirtes.ImageBackground;
import spirtes.SingleColorBackground;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Reads from a blocks definition file.
 */
public class BlocksDefinitionReader {


    /**
     * Creates a factory with the given block and spacer definitions.
     *
     * @param reader file with the definitions.
     * @return a factory that holds all the information
     */
    public static BlocksFromSymbolsFactory fromreader(BufferedReader reader) {

        TreeMap<String, String> defaults = new TreeMap<String, String>();

        TreeMap<String, Integer> spacerWidths = new TreeMap<String, Integer>();

        TreeMap<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();

        //reads through the file and
        //adds each definition to appropriate map
        try {
            String s;
            while (reader.ready()) {
                s = reader.readLine();
                if (s.startsWith("default")) {
                    addToDefaults(s, defaults);
                }
                if (s.startsWith("bdef")) {
                    addToBlockCreators(s, blockCreators, defaults);
                }
                if (s.startsWith("sdef")) {
                    addToSpacerWidths(s, spacerWidths);
                }
            }
        } catch (IOException e) {
            System.out.println("cannot read blocks definition");
            e.printStackTrace();
        }

        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * Adds all the defaults to a map for later use.
     *
     * @param s        string from file
     * @param defaults map of defaults
     */
    private static void addToDefaults(String s, TreeMap<String, String> defaults) {
        String[] defaultAndValue = s.split(" ");

        for (String current : defaultAndValue) {
            if (current.contains(":")) {
                String[] keyAndValue = current.split(":");
                defaults.put(keyAndValue[0], keyAndValue[1]);
            }
        }
    }

    /**
     * Adds a block definition to the map.
     *
     * @param s        string from file
     * @param blocks   map of block creators
     * @param defaults map of defaults
     */
    private static void addToBlockCreators(String s, TreeMap<String, BlockCreator> blocks, TreeMap<String
            , String> defaults) {

        int width;
        int height;
        int hitPoints;
        Fill[] colors;
        Color stroke;
        String symbol;

        TreeMap<String, String> map = new TreeMap<String, String>();

        //splits all the spaces
        String[] pieces = s.split(" ");
        //puts everything with a colon into a map
        for (String thisPiece : pieces) {
            if (thisPiece.contains(":")) {
                String[] keyAndValue = thisPiece.split(":");
                map.put(keyAndValue[0], keyAndValue[1]);
            }
        }

        //find symbol declaration
        if (map.containsKey("symbol")) {
            symbol = map.get("symbol");
        } else if (defaults.containsKey("symbol")) {
            symbol = defaults.get("symbol");
        } else {
            return;
        }

        //finds hitPoints declaration and initializes array of fills
        if (map.containsKey("hit_points")) {
            hitPoints = Integer.parseInt(map.get("hit_points"));
        } else if (defaults.containsKey("hit_points")) {
            hitPoints =
                    Integer.parseInt(defaults.get("hit_points"));
        } else {
            return;
        }
        colors = new Fill[hitPoints + 1];

        //finds width declaration
        if (map.containsKey("width")) {
            width = Integer.parseInt(map.get("width"));
        } else if (defaults.containsKey("width")) {
            width = Integer.parseInt(defaults.get("width"));
        } else {
            return;
        }

        //finds height declaration
        if (map.containsKey("height")) {
            height = Integer.parseInt(map.get("height"));
        } else if (defaults.containsKey("height")) {
            height = Integer.parseInt(defaults.get("height"));
        } else {
            return;
        }

        //finds stroke declaration
        if (map.containsKey("stroke")) {
            stroke = stringToColor(map.get("stroke"));
        } else if (defaults.containsKey("stroke")) {
            stroke = stringToColor(defaults.get("stroke"));
        } else {
            stroke = null;
        }

        //finds default fill declaration and puts at start of array
        if (map.containsKey("fill")) {
            String fillColor = map.get("fill");
            String fillTrimmed = fillColor.substring(
                    fillColor.indexOf("(") + 1,
                    fillColor.indexOf(")")); //trims string
            //checks if to make a color or image
            if (fillColor.startsWith("color")) {
                colors[0] = new SingleColorBackground(fillTrimmed);
            } else if (fillColor.startsWith("image")) {
                colors[0] = new ImageBackground(fillTrimmed);
            } else {
                return;
            }
        }

        //searches for more fills and adds to array respectively
        for (int i = 1; i <= hitPoints; i++) {
            String fillI = "fill-" + i;
            if (map.containsKey(fillI)) {
                String fillColor = map.get(fillI);
                String fillTrimmed = fillColor.substring(
                        fillColor.indexOf("(") + 1,
                        fillColor.indexOf(")")); //trims string
                //checks if to make a color or image
                if (fillColor.startsWith("color")) {
                    colors[i] = new SingleColorBackground(fillTrimmed);
                } else if (fillColor.startsWith("image")) {
                    colors[i] = new ImageBackground(fillTrimmed);
                } else {
                    return;
                }
            }
        }

        //create new blocks.BlockCreator and adds to map
        AbstractBlockCreator tempBlockCreator = new AbstractBlockCreator(width, height, hitPoints, colors, stroke);
        blocks.put(symbol, tempBlockCreator);
    }

    /**
     * Adds a spacer definition to the map.
     *
     * @param s       string from file
     * @param spacers map of spacer definitions
     */
    private static void addToSpacerWidths(String s, TreeMap<String, Integer> spacers) {

        //splits string into 3
        String[] properties = s.split(" ");

        //splits the second 2 into 2
        String[] symbol = properties[1].split(":");
        String[] width = properties[2].split(":");

        //adds to map
        spacers.put(symbol[1], Integer.parseInt(width[1]));

    }

    /**
     * Changes a string to a color.
     *
     * @param frameColor either RGB(x, y, z) or name of a color
     * @return color
     */
    private static Color stringToColor(String frameColor) {

        Color stroke;

        //trims string
        String strokeTrimmed = frameColor.substring(
                frameColor.indexOf("(") + 1,
                frameColor.indexOf(")"));
        //checks if it starts with RGB
        if (strokeTrimmed.startsWith("RGB")) {

            //trims it and splits into 3 integers
            String temp = strokeTrimmed.substring(
                    strokeTrimmed.indexOf("(") + 1, strokeTrimmed.length());
            String[] numbers = temp.split(",");
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            int z = Integer.parseInt(numbers[2]);

            //creates color
            stroke = new Color(x, y, z);

            //should be a color
        } else {
            try {
                java.lang.reflect.Field field =
                        Color.class.getField(strokeTrimmed);
                stroke = (Color) field.get(null);
            } catch (Exception e) {
                stroke = null; // Color not defined
            }
        }
        return stroke;
    }
}

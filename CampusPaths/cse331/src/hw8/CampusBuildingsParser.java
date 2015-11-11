package hw8;


import java.io.*;
import java.util.*;

/**
 * Parser utility to load the Campus Buildings data set.
 */
public class CampusBuildingsParser {
	/* 
	 * This class is not an ADT
	 */
	
    /**
     * A checked exception class for bad data files
     */
    @SuppressWarnings("serial")
    public static class MalformedDataException extends Exception {
        public MalformedDataException() { }

        public MalformedDataException(String message) {
            super(message);
        }

        public MalformedDataException(Throwable cause) {
            super(cause);
        }

        public MalformedDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

  /**
   * Read the campus building data file for the UW Campus.
   * Each line of the input file contains a abbreviated name,
   * long name, and the (x,y) coords, separated by a tab character
   * 
   * ShortName    LongName    x    y
   * 
   * @requires filename is a valid file path
   * @param filename the file that will be read
   * @param buildingNames map in which all abbreviated names are mapped to
   * 		their full name. Typically empty when the routine is called
   * @param buildingCoords map from abbrev of building names to a list that
   *        has the (x,y) coords. Typically empty when the routine is called
   * @modifies buildingNames, buildingCoords
   * @effects fills buildingNames with a mapping of abbreviations to full name
   * @effects fills buildingCoords with a map from each abbreviated name to
   * 		  a list with the (x,y) coords.
   * @throws MalformedDataException if the file is not well-formed:
   *          each line contains exactly two tokens separated by a tab,
   *          or else starting with a # symbol to indicate a comment line.
   */
  public static void parseData(String filename, Map<String,String> buildingNames,
      Map<String, Coordinate> buildingCoords) throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        // Construct the collections of characters and books, one
        // <character, book> pair at a time.
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }

            // Parse the data, stripping out quotation marks and throwing
            // an exception for malformed lines.
            inputLine = inputLine.replace("\"", "");
            String[] tokens = inputLine.split("\t");
            if (tokens.length != 4) {
                throw new MalformedDataException("Line should contain exactly three tabs: "
                                                 + inputLine);
            }

            String shortName = tokens[0];
            String longName = tokens[1];
            double x = Double.parseDouble(tokens[2]);
            double y = Double.parseDouble(tokens[3]);
            
            // Add the parsed data to the returned collections
            Coordinate coords = new Coordinate(x,y);
            buildingCoords.put(shortName, coords);
            
            //No matter what overwrite the abbreviated name and add the full name
            buildingNames.put(shortName, longName);
        }
    } catch (IOException e) {
        System.err.println(e.toString());
        e.printStackTrace(System.err);
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                e.printStackTrace(System.err);
            }
        }
    }
  }

}
package hw8;

import hw5.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Parser utility to load the Campus paths data set.
 */
public class CampusPathsParser {
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
   * Read the campus paths data file for the UW Campus.
   * Each starting section of the input file contains a coordinate
   * in x,y format (separated by one comma). Following this, is a list
   * of coordinates that are connected to this location. 
   * 
   * All connected coordinates are indented by one tab and are in the
   * following format: With exactly 1 tab, 1 comma and 1 semicolon
   * 
   * Format:
   * x1,y1
   *     x2,y2: distance
   *     x3,y3: distance
   *     ...
   * 
   * @requires filename is a valid file path
   * @param filename the file that will be read
   * @param buildingNames map in which all abbreviated names are mapped to
   * 		their full name. Typically empty when the routine is called
   * @param buildingPaths graph with nodes being (x,y) locations and edges are
   * 	    all (x,y) locations connected to the given node.
   * @modifies buildingPaths
   * @effects adds nodes and edges to buildingPaths based on the given
   * 		  file
   * @throws MalformedDataException if the file is not well-formed:
   *          not following format specified above.
   */
  public static void parseData(String filename, Graph<Coordinate,Double> buildingPaths) 
		  throws MalformedDataException {

    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        // Begin with input and a variable for the head coordinate of a section
        String inputLine;
        Node<Coordinate> current = null;
        while ((inputLine = reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }

            // If the line does not start with a tab, then it is a section header
            // Must update current and add information to graph.
            // throws an exception for malformed lines. 
            if (!inputLine.startsWith("\t")) {
            	
            	// This is an (x,y) coord. Split by comma to get the data.
            	String[] tokens = inputLine.split(",");
            	if (tokens.length != 2) {
            		throw new MalformedDataException("Line should contain "
            				+ "exactly one comma: " + inputLine);
            	}
            	
            	// Parse the information to add to our graph
            	double x = Double.parseDouble(tokens[0]);
            	double y = Double.parseDouble(tokens[1]);
            	current = new Node<Coordinate>(new Coordinate(x,y));
            	buildingPaths.addNode(current);
            	
            } else {
                // If the line starts with a tab, then it goes under the current node
            	// And edges are created to represent the 'connections'
            	
            	// if current is null, then the file is not formated properly
            	if(current == null) {
            		throw new MalformedDataException("First line should have not been tabbed");
            	}
            	inputLine.replace("\t", "");
            	//	x2,y2: distance12
            	// First parce by the : to get the coords and distance
            	String[] tokens = inputLine.split(": ");
            	if (tokens.length != 2) {
            		throw new MalformedDataException("Line should contain " 
            				+ "exactly one ': ': " + inputLine);
            	}
            	
            	// Now parse the coordinate part to get x and y
            	String[] coords = tokens[0].split(",");
            	if(coords.length != 2) {
            		throw new MalformedDataException("Line should contain "
            				+ "exactly one comma: " + inputLine);
            	}
            	
            	// Organize the data and put it into our graph
            	double x = Double.parseDouble(coords[0]);
            	double y = Double.parseDouble(coords[1]);
            	double cost = Double.parseDouble(tokens[1]);
            	
            	Node<Coordinate> newCoord = new Node<Coordinate>(new Coordinate(x,y));
            	if(!buildingPaths.containsNode(newCoord)) {
            		buildingPaths.addNode(newCoord);
            	}
            	
            	// Add edge from current to newCoord and newCoord to current (both directions)
            	buildingPaths.addEdge(new Edge<Double,Coordinate>(current, newCoord, cost));
            	buildingPaths.addEdge(new Edge<Double,Coordinate>(newCoord, current, cost));
            }
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
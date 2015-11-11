package hw8;

import java.util.ArrayList;

import hw5.*;
import hw7.DijkstraAlgorithm;


/**
 * The class controls building a graph representing locations on campus, where nodes
 * in the graph are coordinate locations and edges are paths from that location to
 * another location with a given distance.
 */

public class CampusPaths {
	/* 
	 * This class is not an ADT
	 */

	private Graph<Coordinate,Double> campusPaths;
	public static final String FILE_NAME = "src/hw8/data/";
	
	/**
	 * Parses the data in the given file name and builds the graph.
	 * @param fileName to build the graph from in .dat format
	 */
	public void buildData(String fileName) {
		campusPaths = new Graph<Coordinate,Double>();
		
		// Build data from file into graph using parser
		try {
			CampusPathsParser.parseData(FILE_NAME + fileName, campusPaths);
		} catch (hw8.CampusPathsParser.MalformedDataException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds a cost-min path between two coordinate locations.
	 * 
	 * @param start - Coordinate the starting (x,y) location
	 * @param end - Coordinate the ending (x,y) location to be reached
	 * @return a list of edges sorted in the correct path to the ending location
	 */
	public ArrayList<Edge<Double,Coordinate>> findPath(Coordinate start, Coordinate end) {
		//Coordinate startCoord = CampusBuildings.getBuildingCoords(start);
		//Coordinate endCoord = CampusBuildings.getBuildingCoords(end);
		return DijkstraAlgorithm.findPath(campusPaths, start, end, 
				new CampusPathComparator());
	}
	
	/**
	 * 
	 * @param start - String abbreviated name of starting location
	 * @param end - String abbreviated name of ending location
	 * @param longStart - String full name of starting location
	 * @param longEnd - STring full name of ending location
	 * @param startCoord - Coordinate the starting (x,y) location
	 * @param endCoord - Coordinate the ending (x,y) location
	 * @return a string format of a given path between two coordinate locations
	 */
	public String pathToString(String start, String end, String longStart, String longEnd,
			Coordinate startCoord, Coordinate endCoord) {
		
		// Get the coords from the names
		// Also get the full names from the abbreviated names
		//String longStart = CampusBuildings.getLongName(start);
		//String longEnd = CampusBuildings.getLongName(end);
		String result = "";
		Double total = 0.0;
		
		// Case for unknown buildings
	    if(longStart == null || longEnd == null) {
	    	if(longStart == null) {
	    		result += "Unknown building: " + start;
	    	}
	    	if(longStart == null && longEnd == null) {
	    		result += System.lineSeparator() + "Unknown building: " + end;
	    	} else if(longEnd == null) {
	    		result += "Unknown building: " + end;
	    	}
	    	return result;
	    } else {
	    	// If these buildings exist, then we can find a path
			result = "Path from " + longStart
					+ " to " + longEnd + ":";
			
			// Convert from names to coordinates
			//Coordinate startCoord = CampusBuildings.getBuildingCoords(start);
			//Coordinate endCoord = CampusBuildings.getBuildingCoords(end);
			
			// Actually find the paths from these coordinates
			ArrayList<Edge<Double,Coordinate>> path = findPath(startCoord, endCoord);
			if(path == null) {
				return result + System.lineSeparator() + "no path found";
			} else if(start.equals(end)) {
				return result += System.lineSeparator() + String.format("Total distance: %.0f", total) + " feet";
			} else {
				Coordinate prev = startCoord;
				Coordinate current;
				
				// Case for if there is no possible path
				if(path.size() == 0) {
					return result += System.lineSeparator() + "no path found";
				}
				
				// Generate string of path
				for(int i = 2; i < path.size(); i++) {
					current = path.get(i).getParent().getData();
					Double edge = path.get(i-1).getValue();
					result += System.lineSeparator() + String.format("\tWalk %.0f", edge)
							+ " feet " + direction(current,prev) + " to (" + current.toString()
						    + ")";
					prev = current;
					total += edge;
				}
				Double edge = path.get(path.size() - 1).getValue();
				total += edge;
				result += System.lineSeparator() + String.format("\tWalk %.0f", edge) 
				+ " feet " + direction(path.get(path.size()-1).getChild().getData(),prev) 
				+ " to (" + path.get(path.size()-1).getChild().getData().toString() + ")";
			}
	    }
		result += System.lineSeparator() + String.format("Total distance: %.0f", total)
				+ " feet";
		return result;
	}
	
	/**
	 * 
	 * @param start - Coordinate starting (x,y) location
	 * @param end - Coordinate ending (x,y) location
	 * @return the direction from the starting coordinate to the ending coordinate
	 * 		   if these locations are invald, null is returned.
	 */
	private String direction(Coordinate start, Coordinate end) {
		// Get a new coordinate location by comparing the coordinates of the start and end
		Coordinate newCoord = new Coordinate(start.getX() - end.getX(), 
				start.getY() - end.getY());
		
		// Convert to degrees
		double location = -Math.atan2(newCoord.getY(), newCoord.getX());
		double pie = Math.PI;
		
		// Check for the direction based on unit circle
		if(location > (3*pie/8) && location < (5*pie/8)) {
			return "N";
		} else if (location > (pie/8) && location < (3*pie/8)) {
			return "NE";
		} else if (location > (5*pie/8) && location < (7*pie/8)) {
			return "NW";
		} else if (location >= (-5*pie/8) && location <= (-3*pie/8)) {
			 return "S";
		} else if (location > (-3*pie/8) && location < (-pie/8)) {
			return "SE";
		} else if (location > (-7*pie/8) && location < (-5*pie/8)) {
			return "SW";
		} else if (Math.abs(location) <= (pie/8)) { 
			return "E";
		} else if (Math.abs(location) >= (7*pie/8)) {
			return "W";
		} else {
			return null;
		}
	}
		            
}

package hw8;

import java.util.*;

/**
 * 
 * This is the model for UW Campus Paths. 
 * It controls building all the data and contains 'getters'
 * to get necessary data. 
 *
 */
public class CampusModel {

	private static CampusBuildings buildings;
	private static CampusPaths paths;

	/** 
	 * Builds all the necessary data for UW Campus Paths
	 */
	public void buildData() {
		buildings = new CampusBuildings();
		paths = new CampusPaths();
		buildings.buildData("campus_buildings.dat");
		paths.buildData("campus_paths.dat");
	}
	
	/**
	 * Finds a path from a given start name and a given ending name.
	 * @param start - String the name of the starting location
	 * @param end - String the name of the ending location
	 * @return the path between the given start and end, if there is one
	 */
	public String findPath(String start, String end) {
		String l1 = buildings.getLongName(start);
		String l2 = buildings.getLongName(end);
		Coordinate startCoords = buildings.getBuildingCoords(start);
		Coordinate endCoords = buildings.getBuildingCoords(end);
		return paths.pathToString(start, end, l1, l2, startCoords, endCoords);
	}
	
	/**
	 * Gets information about all the buildings and their names.
	 * @return all the buildings names in the form "abbreviated name: full name"
	 */
	public String listBuildings() {
		String result = "Buildings:";// + System.lineSeparator();

		Map<String,String> names = buildings.allNames();
		Set<String> abbreviated = new TreeSet<String>(names.keySet());
		for(String str : abbreviated) {
			result += System.lineSeparator() + "\t" + str + ": " + names.get(str);
		}
		return result;
	}
}

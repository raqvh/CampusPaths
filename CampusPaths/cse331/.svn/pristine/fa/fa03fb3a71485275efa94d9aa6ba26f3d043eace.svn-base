package hw8;

import hw8.CampusBuildingsParser.MalformedDataException;

import java.util.*;

/**
 * 
 * This class controls storing the data for a campus building information,
 * where buildingNames maps the abbreviated names of all the buildings to
 * their full names. And buildingCoords maps the abbreviated names to their
 * location coordinates. Getters are included to get data about these mappings.
 *
 */
public class CampusBuildings {
/*
 * This class is not an ADT
 */
	
	private Map<String,String> buildingNames;
	private Map<String, Coordinate> buildingCoords;
	public static final String FILE_NAME = "src/hw8/data/";

	/**
	 * Builds the data for the campus buildings and coordinates from the
	 * given fileName. 
	 * @requires File must be .dat format where each line contains
	 * abbreviated Name   full Name  x coord   y coord 
	 * All separated by exactly one tab.
	 * @modifies this
	 * @effects stores all the abbreviated names mapped to their full names in 
	 *          buildingNames and stores all abbreviated names mapped to their
	 *          given coordinates in buildingCoords.
	 * 
	 */
	public void buildData(String fileName) {
		buildingNames = new HashMap<String,String>();
		buildingCoords = new HashMap<String,Coordinate>();
			  
		try {
			CampusBuildingsParser.parseData(FILE_NAME + fileName, buildingNames, buildingCoords);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the full name for the abbreviation for the building.
	 * 
	 * @param abbrevName - String: the abbreviated name for the given building
	 * @return the full name for the given abbreviated name. If the name does not
	 *         exist then null is returned.
	 */
	public String getLongName(String abbrevName) {
		if(buildingNames.containsKey(abbrevName)) {
			return buildingNames.get(abbrevName);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the coordinate for the given building abbreviated name.
	 * 
	 * @param abbrevName - String: the abbreviated name for the given building
	 * @return the coordinates for the given abbreviated name. If the name does not
	 *         exist then null is returned.
	 */
	public Coordinate getBuildingCoords(String abbrevName) {
		if(buildingCoords.containsKey(abbrevName)) {
			return buildingCoords.get(abbrevName);
		} else {
			return null;
		}
	}
	
	/**
	 * @return a mapping of all building abbreviated names to their full names
	 */
	public Map<String,String> allNames() {
		return buildingNames;
	}
}

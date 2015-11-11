package hw9;

import java.util.ArrayList;
import java.util.Map;

import hw5.Edge;
import hw8.*;

/**
 * 
 * This class handles the data and has getters for information such as
 * coordinates of a building based on the name, and a list of a path from
 * a starting and ending location.
 *
 */
public class CampusPathList {
	
	private CampusPaths paths;
	private CampusBuildings buildings;
	
	/*
	 * Builds all the data
	 */
	public CampusPathList() {
		paths = new CampusPaths();
		paths.buildData("campus_paths.dat");
		buildings = new CampusBuildings();
		buildings.buildData("campus_buildings.dat");
	}
	
	/*
	 * Returns a list of coordinates that creates a path from the given starting
	 * and ending locations.
	 */
	public ArrayList<Coordinate> pathList(Coordinate start, Coordinate end) {
		ArrayList<Edge<Double,Coordinate>> edgePath = paths.findPath(start,end);
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		for(int i = 1; i < edgePath.size(); i++) {
			Edge<Double,Coordinate> edge = edgePath.get(i);
			coords.add(edge.getParent().getData());
			coords.add(edge.getChild().getData());
		}
		return coords;
	}
	
	/*
	 * Returns the given (x,y) coordinate from a given string name for a UW building.
	 */
	public Coordinate stringToCoord(String name) {
		String token[] = name.split(":");
		return buildings.getBuildingCoords(token[0]);
	}
	
	/*
	 * Returns a mapping of all abbreviated building names to their full name.
	 */
	public Map<String,String> allBuildings() {
		return buildings.allNames();
	}
}

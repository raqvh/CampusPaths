package hw8.test;

import static org.junit.Assert.*;
import hw8.CampusBuildingsParser;
import hw8.CampusBuildingsParser.MalformedDataException;
import hw8.Coordinate;

import java.util.*;

import org.junit.Test;

/**
 * Test class for CampusBuildingsParser.
 * Test that the data is properly parsed.
 *
 */
public class TestBuildingsParser {
	
	public static final String FILE_NAME = "src/hw8/data/campus_buildings.dat";
	private static Map<String,String> names = new HashMap<String,String>();
	private static Map<String,Coordinate> coords = new HashMap<String,Coordinate>();
	
	/**
	 * Test a couple of building names to ensure they are correct.
	 */
	@Test
	public void testBuildingNames() {
		try {
			CampusBuildingsParser.parseData(FILE_NAME, names, coords);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
		
		// Abbreviated names are keys
		assertTrue("Buildings exist in the map", names.containsKey("CSE"));
		assertTrue("Buildings exist in the map", names.containsKey("MGH"));
		assertTrue("Buildings exist in the map", names.containsKey("SAV"));
		assertTrue("Buildings exist in the map", names.containsKey("CMU"));
		
		// Gets correct long names
		assertEquals("Building full name", names.get("CSE"), 
				"Paul G. Allen Center for Computer Science & Engineering");
		assertEquals("Building full name", names.get("MGH"), 
				"Mary Gates Hall (North Entrance)");
		assertEquals("Building full name", names.get("SAV"), 
				"Savery Hall");
		assertEquals("Building full name", names.get("CMU"), 
				"Communications Building");
	}
	
	/**
	 * Test getting a few coordinates to check they are correct.
	 */
	@Test
	public void testBuildingCoords() {
		try {
			CampusBuildingsParser.parseData(FILE_NAME, names, coords);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
		// Gets correct coordinates
		Coordinate c1 = new Coordinate(2259.7112,1715.5273);
		assertEquals("Building coordinate", coords.get("CSE"), c1);
		Coordinate c2 = new Coordinate(1973.1382,1433.6676);
		assertEquals("Building coordinate", coords.get("MGH"), c2);
		Coordinate c3 = new Coordinate(1951.8672,1094.7886);
		assertEquals("Building coordinate", coords.get("SAV"), c3);
		Coordinate c4 = new Coordinate(2344.8512,1114.6251);
		assertEquals("Building coordinate", coords.get("CMU"), c4);
	}

}

package hw8.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import hw8.CampusBuildings;
import hw8.Coordinate;

/**
 * 
 * This is a test class for CampusBuildings.java
 *
 */
public class TestCampusBuildings {

	private CampusBuildings buildings = new CampusBuildings();
	public static final String FILE_NAME = "campus_buildings.dat";
	
	/**
	 * Test that getLongName() returns the correct full name for a building
	 */
	@Test
	public void testGetLongName() {
		buildings.buildData(FILE_NAME);
		
		// Test basic cases
		assertEquals("Building full name", buildings.getLongName("CSE"), 
				"Paul G. Allen Center for Computer Science & Engineering");
		assertEquals("Building full name", buildings.getLongName("MGH"), 
				"Mary Gates Hall (North Entrance)");
		assertEquals("Building full name", buildings.getLongName("SAV"), 
				"Savery Hall");
		assertEquals("Building full name", buildings.getLongName("CMU"), 
				"Communications Building");
		
		// Check returns null for unknown name
		assertEquals("Name for unknown building", buildings.getLongName("WSU"), null);
	}
	
	/**
	 * Test that getBuildingCoords returns the correct coordinates for a building
	 */
	@Test
	public void testGetBuildingCoords() {
		buildings.buildData(FILE_NAME);
		
		// Gets correct coordinates in basic cases
		Coordinate c1 = new Coordinate(2259.7112,1715.5273);
		assertEquals("Building coordinate", buildings.getBuildingCoords("CSE"), c1);
		Coordinate c2 = new Coordinate(1973.1382,1433.6676);
		assertEquals("Building coordinate", buildings.getBuildingCoords("MGH"), c2);
		Coordinate c3 = new Coordinate(1951.8672,1094.7886);
		assertEquals("Building coordinate", buildings.getBuildingCoords("SAV"), c3);
		Coordinate c4 = new Coordinate(2344.8512,1114.6251);
		assertEquals("Building coordinate", buildings.getBuildingCoords("CMU"), c4);
		
		// Check returns null for unknown name
		assertEquals("Coordinate for unknown building", buildings.getBuildingCoords("WSU"), null);
	}
	
	/**
	 * Test that allNames() has correct mappings from abbreviated to full name
	 */
	@Test
	public void testAllNames() {
		buildings.buildData(FILE_NAME);
		
		Map<String,String> names = buildings.allNames();
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
}

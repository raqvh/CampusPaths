package hw8.test;

import static org.junit.Assert.*;
import hw8.CampusBuildings;
import hw8.CampusPaths;

import org.junit.Test;

/**
 * A test class for CampusPaths.txt
 * Tests that it gets correct paths as needed.
 *
 */
public class TestCampusPaths {
	/*
	 * Note: Because TestPathsParser already test the correct parsing of
	 *       the path data, buildData() is not retested here, since it simply
	 *       calls the path parsing class. This will also be tested in the .test
	 *       and .expected files. 
	 *       CampusPaths deals more with actually finding a path, which is tested for 
	 *       correctness here.
	 */
	
	private static CampusPaths paths = new CampusPaths();
	private static CampusBuildings buildings = new CampusBuildings();
	
	/**
	 * Tests that correct paths are found
	 */
	@Test
	public void testPathToString() {
		paths.buildData("campus_paths.dat");
		buildings.buildData("campus_buildings.dat");
		
		// Sample basic test
		String start = "OUG";
		String end = "KNE";
		
		// Expected result:
		String result =
		"Path from Odegaard Undergraduate Library to Kane Hall (North Entrance):" + System.lineSeparator() +
			"\tWalk 63 feet E to (1754, 1207)" + System.lineSeparator() +
			"\tWalk 79 feet N to (1759, 1170)" + System.lineSeparator() +
			"\tWalk 172 feet NE to (1811, 1129)" + System.lineSeparator() +
			"\tWalk 78 feet E to (1846, 1117)" + System.lineSeparator() +
			"\tWalk 73 feet SE to (1872, 1141)" + System.lineSeparator() +
			"\tWalk 49 feet SE to (1890, 1159)" + System.lineSeparator() +
			"\tWalk 27 feet SW to (1877, 1165)" + System.lineSeparator() +
		"Total distance: 542 feet";

		// Actual results:
		String actual = paths.pathToString(start, end, buildings.getLongName(start), 
				buildings.getLongName(end), buildings.getBuildingCoords(start), 
				buildings.getBuildingCoords(end));
		
		assertEquals("Correct path", result, actual);

	}
}

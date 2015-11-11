package hw8.test;

import static org.junit.Assert.assertEquals;
import hw8.CampusModel;

import org.junit.Test;

/**
 * 
 * This is a test class for CampusModelTest.java.
 * It test for correctness within the class.
 *
 */
public class CampusModelTest {
/*
* This class is not an ADT
*/
	
	CampusModel model = new CampusModel();
	
	/**
	 * Test that the proper path is returned.
	 */
	@Test
	public void testFindPath() {

		model.buildData();
		
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
		String actual = model.findPath(start, end);
		
		assertEquals("Correct path", result, actual);
	}
	
	/**
	 * Test proper format is returned in the case of an unknown building.
	 */
	@Test
	public void testFindPathUnknown() {		
		// first is unknown
		String actual = model.findPath("OOG","CSE");
		String result = "Unknown building: OOG";
		
		assertEquals("First building unknown", actual, result);
		
		// second is unknown
		actual = model.findPath("CSE","OOG");
		result = "Unknown building: OOG";
		
		assertEquals("Second building unknown", actual, result);
		
		// two are unknown
		actual = model.findPath("GOO","GEE");
		result = "Unknown building: GOO" + System.lineSeparator()
						+ "Unknown building: GEE";
		
		assertEquals("First building unknown", actual, result);
	}
	
	/**
	 * Test proper format is returned in the case of a path to itself.
	 */
	@Test
	public void testFindPathSame() {

		model.buildData();
		
		// Sample basic test
		String start = "MGH";
		String end = "MGH";
		
		// Expected result:
		String result =
		"Path from Mary Gates Hall (North Entrance) to Mary Gates Hall (North Entrance):" 
		+ System.lineSeparator() + "Total distance: 0 feet";
;

		// Actual results:
		String actual = model.findPath(start, end);
		
		assertEquals("Correct path", result, actual);
	}
	
	/**
	 * Test all buildings are listed out and in the proper format.
	 */
	@Test
	public void testListBuildings() {
		model.buildData();
		String actual = model.listBuildings();
		String result = 
				"Buildings:" + System.lineSeparator()
					+ "\tBAG: Bagley Hall (East Entrance)" + System.lineSeparator()
					+ "\tBAG (NE): Bagley Hall (Northeast Entrance)" + System.lineSeparator()
					+ "\tBGR: By George" + System.lineSeparator()
					+ "\tCHL: Chemistry Library (West Entrance)" + System.lineSeparator()
					+ "\tCHL (NE): Chemistry Library (Northeast Entrance)" + System.lineSeparator()
					+ "\tCHL (SE): Chemistry Library (Southeast Entrance)" + System.lineSeparator()
					+ "\tCMU: Communications Building" + System.lineSeparator()
					+ "\tCSE: Paul G. Allen Center for Computer Science & Engineering" + System.lineSeparator()
					+ "\tDEN: Denny Hall" + System.lineSeparator()
					+ "\tEEB: Electrical Engineering Building (North Entrance)" + System.lineSeparator()
					+ "\tEEB (S): Electrical Engineering Building (South Entrance)" + System.lineSeparator()
					+ "\tFSH: Fishery Sciences Building" + System.lineSeparator()
					+ "\tGWN: Gowen Hall" + System.lineSeparator()
					+ "\tHUB: Student Union Building (Main Entrance)" + System.lineSeparator()
					+ "\tHUB (Food, S): Student Union Building (South Food Entrance)" + System.lineSeparator()
					+ "\tHUB (Food, W): Student Union Building (West Food Entrance)" + System.lineSeparator()
					+ "\tIMA: Intramural Activities Building" + System.lineSeparator()
					+ "\tKNE: Kane Hall (North Entrance)" + System.lineSeparator()
					+ "\tKNE (E): Kane Hall (East Entrance)" + System.lineSeparator()
					+ "\tKNE (S): Kane Hall (South Entrance)" + System.lineSeparator()
					+ "\tKNE (SE): Kane Hall (Southeast Entrance)" + System.lineSeparator()
					+ "\tKNE (SW): Kane Hall (Southwest Entrance)" + System.lineSeparator()
					+ "\tLOW: Loew Hall" + System.lineSeparator()
					+ "\tMCC: McCarty Hall (Main Entrance)" + System.lineSeparator()
					+ "\tMCC (S): McCarty Hall (South Entrance)" + System.lineSeparator()
					+ "\tMCM: McMahon Hall (Northwest Entrance)" + System.lineSeparator()
					+ "\tMCM (SW): McMahon Hall (Southwest Entrance)" + System.lineSeparator()
					+ "\tMGH: Mary Gates Hall (North Entrance)" + System.lineSeparator()
					+ "\tMGH (E): Mary Gates Hall (East Entrance)" + System.lineSeparator()
					+ "\tMGH (S): Mary Gates Hall (South Entrance)" + System.lineSeparator()
					+ "\tMGH (SW): Mary Gates Hall (Southwest Entrance)" + System.lineSeparator()
					+ "\tMLR: Miller Hall" + System.lineSeparator()
					+ "\tMNY: Meany Hall (Northeast Entrance)" + System.lineSeparator()
					+ "\tMNY (NW): Meany Hall (Northwest Entrance)" + System.lineSeparator()
					+ "\tMOR: Moore Hall" + System.lineSeparator()
					+ "\tMUS: Music Building (Northwest Entrance)" + System.lineSeparator()
					+ "\tMUS (E): Music Building (East Entrance)" + System.lineSeparator()
					+ "\tMUS (S): Music Building (South Entrance)" + System.lineSeparator()
					+ "\tMUS (SW): Music Building (Southwest Entrance)" + System.lineSeparator()
					+ "\tOUG: Odegaard Undergraduate Library" + System.lineSeparator()
					+ "\tPAA: Physics/Astronomy Building A" + System.lineSeparator()
					+ "\tPAB: Physics/Astronomy Building" + System.lineSeparator()
					+ "\tPAR: Parrington Hall" + System.lineSeparator()
					+ "\tRAI: Raitt Hall (West Entrance)" + System.lineSeparator()
					+ "\tRAI (E): Raitt Hall (East Entrance)" + System.lineSeparator()
					+ "\tROB: Roberts Hall" + System.lineSeparator()
					+ "\tSAV: Savery Hall" + System.lineSeparator()
					+ "\tSUZ: Suzzallo Library" + System.lineSeparator()
					+ "\tT65: Thai 65" + System.lineSeparator()
					+ "\tUBS: University Bookstore" + System.lineSeparator()
					+ "\tUBS (Secret): University Bookstore (Secret Entrance)";
		
		assertEquals("Correct building list", result, actual);
	}	
}

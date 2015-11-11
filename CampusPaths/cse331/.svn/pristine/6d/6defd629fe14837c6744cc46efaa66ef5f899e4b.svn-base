package hw8.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hw8.Coordinate;


/**
 * This class contains test cases that can be used to test the implementation
 * of the Coordinate class.
 */
public class CoordinateTest {

	// A few general coordinates for testing purpose
	Coordinate origin = new Coordinate();
	Coordinate basic = new Coordinate(5,5);
	Coordinate equalsBasic = new Coordinate(5,5);
	Coordinate basic2 = new Coordinate(4,9);
	
	// Test the method getX() for proper returns 
	// Should return the x value for the given coord
	@Test
	public void testGetX() {
		//String verify = "Returns the correct X value for a Coordinate";
	    assertEquals("Returns the correct X value for a Coordinate", 
	    		origin.getX(), 0.0, 0.0);
	    assertEquals("Returns the correct X value for a Coordinate", 
	    		basic.getX(), 5.0, 0.0);
	    assertEquals("Returns the correct X value for a Coordinate", 
	    		basic2.getX(), 4.0, 0.0);
	}
	
	// Test the method getY() for proper returns 
	// Should return the x value for the given coord
	@Test
	public void testGetY() {
		//String verify = "Returns the correct Y value for a Coordinate";
	    assertEquals("Returns the correct Y value for a Coordinate", 
	    		origin.getY(), 0.0, 0.0);
	    assertEquals("Returns the correct Y value for a Coordinate", 
	    		basic.getY(), 5.0, 0.0);
	    assertEquals("Returns the correct Y value for a Coordinate", 
	    		basic2.getY(), 9.0, 0.0);
	}
	
	/**
	 * Test that toString returns the expected string - (x, y)
	 */
	@Test
	public void testToString() {
		assertEquals("Proper string format", basic.toString(), "5, 5");
	}
	
	@Test 
	public void testEquals() {
		// Reflexive case
		assertTrue("Reflexive case", basic.equals(basic));
		
		// Symmetric case
		assertTrue("Symmetric case", basic.equals(equalsBasic));
		assertTrue("Symmetric case", equalsBasic.equals(basic));
		
		// Transitive case
		Coordinate thirdBasic = new Coordinate(5,5);
		assertTrue("Transitive case", basic.equals(thirdBasic));
		assertTrue("Transitive case", equalsBasic.equals(thirdBasic));
		assertTrue("Transitive case", basic.equals(equalsBasic));
		
		// Not equal case (false positives)
		assertFalse("Check for false positives", basic.equals(origin));
	}
	
	/*
	 * Test compareTo returns the correct expected value
	 */
	@Test
	public void testCompareTo() {
		// Positive integer - this > other
		Coordinate farther = new Coordinate(10,10);
		assertTrue("Must be greater than 0", farther.compareTo(basic) > 0);
		
		// Negative integer
		assertTrue("Must be less than 0", basic.compareTo(farther) < 0);
		
		// 0 for equals
		assertEquals("Expecting 0", basic.compareTo(equalsBasic), 0);
	}
	
	/** 
	 * Test that the correct distance is returned
	 */
	@Test
	public void testDistance() {
		Coordinate newBasic = new Coordinate(4,4);
		assertTrue("Correct distance", newBasic.distance(basic2) == 5);
	}
	
	/**
	 * Test that the hashCode() function meets the hashtable contract
	 */
	@Test
	public void testHashCode() {
		// Same hashcode must be returned for equal objects
		assertTrue("Should have same hash code", basic.hashCode() == equalsBasic.hashCode());
	}

}


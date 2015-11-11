package hw5.test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Test;

import hw5.*;

/**
 * This class contains test cases that can be used to test the implementation
 * of the Node class.
 */
public class NodeTest {
	
	//General Nodes for testing
	private Node a = new Node("A");
	private Node b = new Node("B");
	private Node c = new Node("C");
	
	//Node with blank data for testing
	private Node blank = new Node();
	
	//After equals has been tested, it can be used in the rest of the test code
	@Test
	public void testEquals() {
		//Reflexive case x.equals(x) is always true;
	    assertTrue("A node equals itself", a.equals(a));
	    assertTrue("A blank node equals itself", blank.equals(blank));
	    
	    //Check for false positives
	    assertTrue("Two different nodes are not equal", !a.equals(b));
	    assertTrue("Two different nodes are not equal flipped", !b.equals(a));
	    assertTrue("Blank node is not equal to non blank", !blank.equals(a));
	    assertTrue("Nonblank is not equal to blank", !a.equals(blank));
	    
	    //Just general check
	    Node like_a = new Node("A");
	    assertEquals("Two different but same data nodes are eqaul", a, like_a);
	}
	
	@Test
	public void testConstructor() {
		//Test that we can actually do this for both constructors.
		new Node("Data");
		new Node();
	}
	
	@Test
	public void testToString() {
		assertEquals("Produces proper toString()", a.toString(), "Node: A");
		assertEquals("Produces proper toString() on blank", blank.toString(), "Node: ");
	}
	
	@Test
	public void testGetData() {	
		assertEquals("Gets data correctly", a.getData(), "A");
		assertEquals("Gets blank data correctly", blank.getData(), "");
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue("Check empty if it is empty", blank.isEmpty());
		assertTrue("Check not empty if it is empty", !a.isEmpty());
	}
	
	@Test
	public void testCompareTo() {
		int num = a.compareTo(b);
		assertTrue("Compare less to greater", num < 0);
		num = b.compareTo(a);
		assertTrue("Compare greater to less", num > 0);
		num = b.compareTo(b);
		assertTrue("COmpare equals", num == 0);
	}
	
	@Test
	public void testHashCode() {
		int num = a.hashCode();
		int num2 = b.hashCode();
		assertTrue("Compare non different hash code", num != num2);
		
		Node like_a = new Node("A");
		num = a.hashCode();
		num2 = like_a.hashCode();
		assertTrue("Compares to equal hashcodes", num == num2);
	}
}

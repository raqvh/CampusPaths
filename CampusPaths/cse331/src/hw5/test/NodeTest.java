package hw5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hw5.*;

/**
 * This class contains test cases that can be used to test the implementation
 * of the Node<String> class.
 */
public class NodeTest {
	
	//General Nodes for testing
	private Node<String> a = new Node<String>("A");
	private Node<String> b = new Node<String>("B");
	private Node<String> c = new Node<String>("C");
	
	//After equals has been tested, it can be used in the rest of the test code
	@Test
	public void testEquals() {
		//Reflexive case x.equals(x) is always true;
	    assertTrue("A node equals itself", a.equals(a));
	    
	    //Check for false positives
	    assertTrue("Two different nodes are not equal", !a.equals(b));
	    assertTrue("Two different nodes are not equal flipped", !b.equals(a));
	    
	    //Just general check
	    Node<String> like_a = new Node<String>("A");
	    assertEquals("Two different but same data nodes are equal", a, like_a);
	}
	
	@Test
	public void testConstructor() {
		//Test that we can actually do this for both constructors.
		new Node<String>("Data");
	}
	
	@Test
	public void testToString() {
		assertEquals("Produces proper toString()", a.toString(), "Node: A");
	}
	
	@Test
	public void testGetData() {	
		assertEquals("Gets data correctly", a.getData(), "A");
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
		
		Node<String> like_a = new Node<String>("A");
		num = a.hashCode();
		num2 = like_a.hashCode();
		assertTrue("Compares to equal hashcodes", num == num2);
	}
}

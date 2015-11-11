package hw5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hw5.*;

/**
 * This class contains test cases that can be used to test the implementation
 * of the Edge<String,String> class.
 */
public final class EdgeTest {

	// naming convention spells out which
	// nodes and data are used in the edge

	// Nodes for using in Edges (See NodeTest for Node<String> testing)
	private Node<String> a = new Node<String>("A");
	private Node<String> b = new Node<String>("B");
	private Node<String> c = new Node<String>("C");
	
	// Simple Edge<String,String> cases
	private Edge<String,String> a_to_b = new Edge<String,String>(a, b, "AB");
	private Edge<String,String> b_to_a = new Edge<String,String>(b, a, "BA");
	private Edge<String,String> a_to_c = new Edge<String,String>(a, c, "AC");
	private Edge<String,String> b_to_c = new Edge<String,String>(b, c, "BC");
	
	// Edge<String,String> 'loop' case.
	private Edge<String,String> a_to_a = new Edge<String,String>(a, a, "AA");
	
	// After equals has been tested, it can be used in the rest of the test code
	@Test
	public void testEquals() {
		// Reflexive case x.equals(x) is always true;
	    assertTrue("An edge equals itself", a_to_b.equals(a_to_b));
	    
	    // Symmetric
	    Edge<String,String> equalA = new Edge<String,String>(a, b, "equal");
	    Edge<String,String> equalB = new Edge<String,String>(a, b, "equal");
	    assertTrue("a == b, b == a", equalA.equals(equalB));
	    assertTrue("a == b, b == a", equalB.equals(equalA));
	    
	    // Transitive
	    Edge<String,String> equalC = new Edge<String,String>(a, b, "equal");
	    assertTrue("c == a, c == b, a == b", equalC.equals(equalA));
	    assertTrue("c == a, c == b, a == b", equalC.equals(equalB));
	    assertTrue("c == a, c == b, a == b", equalA.equals(equalB));
	    
	    // Check for false positives
	    assertTrue("Two different nodes are not equal", !a_to_b.equals(b_to_a));
	    assertTrue("Two different nodes are not equal flipped", !b_to_a.equals(a_to_b));
	    assertTrue("Same start, different end", !a_to_c.equals(b_to_c));
	    assertTrue("Different end, same start", !a_to_b.equals(a_to_c));
	    
	}
	
	// Test that we can actually construct an edge
	@Test
	public void testConstructor() {
		// blank Edge<String,String>
		new Edge<String,String>(new Node<String>(""), new Node<String>(""), "");
		
		// General Edges
		new Edge<String,String>(new Node<String>("start"), new Node<String>("end"), "edge");
		new Edge<String,String>(new Node<String>(""), new Node<String>("end"), "edge");
		new Edge<String,String>(new Node<String>("start"), new Node<String>(""), "edge");
	}
	
	@Test
	public void testToString() {
		assertEquals("Produces proper toString()", a_to_b.toString(),
				"Edge: AB[Node: A, Node: B]");
		assertEquals("Produces proper toSttring() on loop", a_to_a.toString(), 
				"Edge: AA[Node: A, Node: A]");
	}
	
	@Test
	public void testGetValue() {	
		assertEquals("Gets value correctly", a_to_b.getValue(), "AB");
	}
	
	@Test
	public void testGetParent() {
		//Check returns the correct parent node.
		assertEquals(a_to_b.getParent(), a);
		assertEquals(b_to_a.getParent(), b);
		assertEquals(a_to_a.getParent(), a);
	}
	
	@Test 
	public void testGetChild() {
		//Check returns the correct child node.
		assertEquals(a_to_b.getChild(), b);
		assertEquals(b_to_a.getChild(), a);
		assertEquals(a_to_a.getChild(), a);
	}
	
	@Test
	public void testHashCode() {
		int num = a_to_b.hashCode();
		int num2 = b_to_a.hashCode();
		assertTrue("Compare non different hash code", num != num2);
		
		Edge<String,String> like_a = new Edge<String,String>(new Node<String>("A"), 
				new Node<String>("B"), "AB");
		num = a_to_b.hashCode();
		num2 = like_a.hashCode();
		assertTrue("Compares to equal hashcodes", num == num2);
	}
}
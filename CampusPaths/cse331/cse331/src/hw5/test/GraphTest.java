package hw5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;
import java.lang.*;

import hw5.*;

/**
 * This class contains test cases that can be used to test the implementation
 * of the Graph class.
 */

public class GraphTest {

	private Graph test;
	private Graph emptyGraph;
	
	private Node a = new Node("A");
	private Node b = new Node("B");
	private Node c = new Node("C");
	
	private Edge a_to_a = new Edge(a, a, "AA");
	private Edge a_to_b = new Edge(a, b, "AB");
	private Edge a_to_c = new Edge(a, c, "AC");
	private Edge b_to_a = new Edge(b, a, "BA");
	private Edge b_to_c = new Edge(b, c, "BC");
	private Edge c_to_a = new Edge(c, a, "CA");
	private Edge c_to_b = new Edge(c, b, "CB");
	
	// graph with Nodes: a, b, c edges: AA[a,a] AB[a,b] AC[a,c] BA[b,a] BC[b,c] CA[c,a] CB[c,b]
	
	@Test
	public void testAddNode() {
		test = new Graph();
		
		// Test general adding of valid nodes
		test.addNode(a); 
		test.addNode(b);
		test.addNode(c);
		
		// Check these are within the graph
		Set<Node> nodes = new HashSet<Node>();
		nodes.add(new Node("A"));
		nodes.add(new Node("B"));
		nodes.add(new Node("C"));
		
		for(Node current : nodes) {
			assertTrue("Graph contains added node", test.containsNode(current));
		}
		
	}
	
	@Test 
	public void testContainsNode() {
		test = new Graph();
		
		test.addNode(a);
		test.addNode(b);
		test.addNode(c);
		
		// Check for correctness (very similar to addNode check)
		Set<Node> nodes = new HashSet<Node>();
		nodes.add(new Node("A"));
		nodes.add(new Node("B"));
		nodes.add(new Node("C"));
		
		for(Node current : nodes) {
			assertTrue("Graph contains added node", test.containsNode(current));
		}
		
		// Check for false positives
		nodes.clear();
		nodes.add(new Node("Z"));
		nodes.add(new Node("Y"));
		nodes.add(new Node("X"));
		
		for(Node current : nodes) {
			assertFalse("Graph does not contain node", test.containsNode(current));
		}
	}
	
	@Test
	public void testRemoveNode() {
		test = new Graph();
		
		// Add nodes
		test.addNode(a);
		test.addNode(b);
		test.addNode(c);
		
		// Remove node and check
		test.removeNode(a);
		assertFalse("Successfully removed node.", test.containsNode(a));
		
		// Remove all nodes and check isEmpty()
		test.removeNode(b);
		test.removeNode(c);
		assertTrue("All nodes succesfully removed", test.isEmpty());
		
		// Remove node and check all edges with node are removed
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		// Now remove a. All added edges should also be removed since they contain a
		test.removeNode(a);
		assertTrue("All nodes and edges containing node removed", test.edgeList().isEmpty());		
	}
	
    /**
     * Tests that removeNode throws an IllegalArgumentException
     * for a nonexistant node.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedExceptionRemoveNode() {
        test = new Graph();
        test.addNode(a); test.addNode(b);
        test.removeNode(new Node("Z"));
    }

	// After clear has been tested it can be used in the rest of the test program
	public void testClear() {
		
		// Add nodes and edges
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		// Remove everything and check isEmpty()
		test.clear();
		assertTrue("All nodes and edges cleared.", test.isEmpty());
	}
	
	/**
	 * Test that edges are properly added into the graph. Will not work if containsEdge() 
	 * is not working properly.
	 */
	@Test
	public void testAddEdge() {
		// Add valid edges with nodes already in graph
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		Set<Edge> edges = new HashSet<Edge>();
		edges.add(a_to_b); edges.add(b_to_a); edges.add(c_to_a);
		// Test all the edges are contained within the map
		for(Edge temp : edges) {
			assertTrue("All edges contained within the graph", test.containsEdge(temp));
		}
	}
	
	/**
	 * Test that addEdge throws an IllegalArgumentException
	 * for an edge with a node not in the map.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void expectedExceptionAddEdge() {
		test = new Graph();
		test.addEdge(b_to_c);
	}
	
	/**
	 * Test that contains is working properly. Will not work if addEdge() is not working.
	 */
	@Test
	public void testContainsEdge() {
		// Add edges to the graph and check if they are contained
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		Set<Edge> edges = new HashSet<Edge>();
		edges.add(a_to_b); edges.add(b_to_a); edges.add(c_to_a);
		// Test all the edges are contained within the map
		for(Edge temp : edges) {
			assertTrue("All edges contained within the graph", test.containsEdge(temp));
		}
		
		// Test for false positives
		edges.clear();
		edges.add(a_to_c); edges.add(b_to_c); edges.add(c_to_b);
		for(Edge temp : edges) {
			assertFalse("All edges not contained in this graph", test.containsEdge(temp));
		}
		
	}
	
	/**
	 * Test existing edges are properly removed from graph.
	 */
	@Test 
	public void testRemoveEdge() {
		// Check for removing existant edges
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		Set<Edge> edges = new HashSet<Edge>();
		edges.add(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		for(Edge temp : edges) {
			test.removeEdge(temp);
			assertFalse("Removed an edge", test.containsEdge(temp));
		}
	}
	
    /**
     * Tests that removeEdge throws an IllegalArgumentException
     * for a nonexistent Edge.
     */
    @Test(expected=IllegalArgumentException.class)
    public void expectedExceptionRemoveEdge() {
        test = new Graph();
        test.addNode(a); test.addNode(b);
        test.removeEdge(b_to_a);
    }
	
    /**
     * Test that edgeList returns a correct list of all the edges within the graph.
     */
	@Test
	public void testEdgeList() {
		// Check return edgeList contains all added edges
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		
		Set<Edge> edges = new HashSet<Edge>();
		edges.add(a_to_b); test.addEdge(b_to_a); test.addEdge(c_to_a);
		List<Edge> allEdges = test.edgeList();
		for(Edge temp : edges) {
			assertTrue("List contains existing edge.", allEdges.contains(temp));
		}
	}
	
	/**
	 * Test that getChildren returns all the children of a given node.
	 */
	@Test
	public void testGetChildren() {
		// Check returned set contains all children for the given node
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		test.addEdge(a_to_a); test.addEdge(a_to_b); test.addEdge(a_to_c);
		Set<Node> correctChildren = new HashSet<Node>();
		correctChildren.add(a); correctChildren.add(b); correctChildren.add(c);
		
		// Test all children nodes are returned in the list
		Set<Node> actualChildren = test.getChildren(a);
		for(Node temp : correctChildren) {
			assertTrue("Correct children returned", actualChildren.contains(temp));
		}
		
		// Check for false returns on a node with no children
		Graph testEmpty = new Graph();
		testEmpty.addNode(b);
		actualChildren = testEmpty.getChildren(b);
		assertTrue("No children for this node.", actualChildren.isEmpty());
	}
	
	@Test
	public void testSize() {
		test = new Graph();
		int size = 0;
		// Size is 0
		assertEquals("Graph with size of 0", test.size(), size);
		
		// Add and test size
		test.addNode(a); size++;
		assertEquals("Graph with one key-value mapping", test.size(), size);
		test.addNode(b); size++;
		test.addNode(c); size++;
		assertEquals("Graph with multiple mappings", test.size(), size);
		
		// Remove and test size is properly updated
		test.removeNode(a); size--;
		assertEquals("Removed a node", test.size(), size);
		test.clear(); size = 0;
		assertEquals("Removed all nodes and edges", test.size(), size);
	}
	
	/**
	 * Test that isEmpty() is correct in all cases.
	 */
	@Test
	public void testIsEmpty() {
		test = new Graph();
		
		// Graph is currently empty
		assertTrue("Empty graph.", test.isEmpty());
		
		// Check for false positives
		test.addNode(new Node());
		assertFalse("Nonempty graph.", test.isEmpty());
	}
	
	/**
	 * Check returns proper set of all nodes in the graph
	 */
	@Test
	public void testNodeSet() {
		test = new Graph();
		test.addNode(a); test.addNode(b); test.addNode(c);
		Set<Node> expected = new HashSet<Node>();
		expected.add(a); expected.add(b); expected.add(c);
		
		Set<Node> actual = test.nodeSet();
		for(Node temp : expected) {
			assertTrue("Graph node set contains all nodes in graph", 
						actual.contains(temp));
		}
	}
	
}

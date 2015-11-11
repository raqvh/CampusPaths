package hw8.test;

import static org.junit.Assert.*;

import org.junit.Test;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw8.CampusPathsParser;
import hw8.CampusPathsParser.MalformedDataException;
import hw8.Coordinate;

/**
 * 
 * Test class for CampusPathsParser.java
 * Checks that the data is properly parsed
 *
 */
public class TestPathsParser {
	
	public static final String FILE_NAME = "src/hw8/data/campus_paths.dat";
	private static Graph<Coordinate,Double> graph = new Graph<Coordinate,Double>();
	
	/**
	 * Test graph has correct information in it after being built.
	 */
	@Test
	public void testCorrectInfo() {
		try {
			CampusPathsParser.parseData(FILE_NAME, graph);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
		
		// Test several random nodes are within the graph
		Node<Coordinate> n1 = new Node<Coordinate>(new Coordinate(2337.0143,806.8278));
		Node<Coordinate> n2 = new Node<Coordinate>(new Coordinate(1690.8912,1736.7662));
		Node<Coordinate> n3 = new Node<Coordinate>(new Coordinate(1697.1429,858.28571));
		Node<Coordinate> n4 = new Node<Coordinate>(new Coordinate(1877.0103,1427.3816));
		assertTrue("Graph conatins node.", graph.containsNode(n1));
		assertTrue("Graph conatins node.", graph.containsNode(n2));
		assertTrue("Graph conatins node.", graph.containsNode(n3));
		assertTrue("Graph conatins node.", graph.containsNode(n4));

		// Test several random edges are within the graph 
		Node<Coordinate> en1 = new Node<Coordinate>(new Coordinate(2365.2722,2084.0056));
		Node<Coordinate> en2 = new Node<Coordinate>(new Coordinate(2376.2324,2077.9952));
		
		Edge<Double,Coordinate> e1 = new Edge<Double,Coordinate>(en1, en2, 26.407481764621828);
		Edge<Double,Coordinate> e2 = new Edge<Double,Coordinate>(en2, en1, 26.407481764621828);
		assertTrue("Graph contains edge.", graph.containsEdge(e1));
		assertTrue("Graph contains edge.", graph.containsEdge(e2));
	}
}

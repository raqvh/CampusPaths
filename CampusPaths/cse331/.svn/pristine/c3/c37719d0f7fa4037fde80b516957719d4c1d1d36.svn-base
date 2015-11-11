package hw8.test;

import static org.junit.Assert.*;
import hw5.Edge;
import hw5.Node;
import hw8.CampusPathComparator;
import hw8.Coordinate;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * This is a test class to test CampusPathComparator.java
 *
 */
public class PathComparatorTest {
/*
 * This class is not an ADT
 */
	CampusPathComparator comp = new CampusPathComparator();
	
	ArrayList<Edge<Double,Coordinate>> smallerCost = new ArrayList<Edge<Double,Coordinate>>();
	ArrayList<Edge<Double,Coordinate>> largerCost = new ArrayList<Edge<Double,Coordinate>>();
	ArrayList<Edge<Double,Coordinate>> equalCost = new ArrayList<Edge<Double,Coordinate>>();
	
	// Some example coordinates
	Coordinate c1 = new Coordinate(1,1);
	Coordinate c2 = new Coordinate(2,3);
	Coordinate c3 = new Coordinate(4,2);
	Coordinate c4 = new Coordinate(0,0);
	
	// Some example nodes for creating edges
	Node<Coordinate> n1 = new Node<Coordinate>(c1);
	Node<Coordinate> n2 = new Node<Coordinate>(c2);
	Node<Coordinate> n3 = new Node<Coordinate>(c3);
	Node<Coordinate> n4 = new Node<Coordinate>(c4);
	
	// Some example edges
	Edge<Double,Coordinate> e1 = new Edge<Double,Coordinate>(n1,n2,1.0);
	Edge<Double,Coordinate> e2 = new Edge<Double,Coordinate>(n1,n3,2.0);
	Edge<Double,Coordinate> e3 = new Edge<Double,Coordinate>(n1,n4,3.0);
	Edge<Double,Coordinate> e4 = new Edge<Double,Coordinate>(n2,n3,4.0);
	Edge<Double,Coordinate> e5 = new Edge<Double,Coordinate>(n2,n4,5.0);
	
	@Test
	public void testCompare() {
		smallerCost.add(e1); smallerCost.add(e2); smallerCost.add(e3);
		equalCost.add(e1); equalCost.add(e3); equalCost.add(e2);
		largerCost.add(e3); largerCost.add(e4); largerCost.add(e5);
		
		// Equal - should be zero
		assertTrue("Must be equal", (comp.compare(smallerCost,equalCost) == 0));
		
		// Greater than - should be > 0
		assertTrue("Must be greater than", (comp.compare(largerCost, smallerCost) > 0));
		
		// Less than - should be < 0 
		assertTrue("Must be less than", (comp.compare(smallerCost, largerCost) < 0));
	}
}

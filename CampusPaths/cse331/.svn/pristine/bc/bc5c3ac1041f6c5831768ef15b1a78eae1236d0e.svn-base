package hw7;

import java.util.*;

import hw5.*;

/**
 * 
 * This class contains methods which will search for a path in a given graph
 * using the Dijkstra Algorithm
 *
 */
public class DijkstraAlgorithm {
	/**
	 * This class is not a ADT
	 */
		
	/**
	 * If the loop terminates then there exist no path from the start to the end.
	 * @requires Edge weights must be >= 0.0
	 * @param <N> The type of data being stored in a graph's node
	 * @param first The 'beginning' of the path
	 * @param second The destination to search for in the graph
	 * @return Returns an ArrayList of Edges that has a path from first to second
	 * 		   returns an empty ArrayList if no path is found.
	 */
	public static <N extends Comparable<N>> ArrayList<Edge<Double,N>> findPath(Graph<N,Double> graph,
			N first, N second, Comparator<? super ArrayList<Edge<Double, N>>> comp) {
		// Starting and destination nodes
		Node<N> start = new Node<N>(first);
		Node<N> dest = new Node<N>(second);
		
		// Each element is a path from start to a given node. 
		// Path's 'priority' is the total cost of the path
		// Uses a comparator to be able to sort a PriorityQueue of ArrayLists
		Queue<ArrayList<Edge<Double,N>>> active = new PriorityQueue<ArrayList<Edge<Double,N>>>(15,comp);
		
		// Set of nodes for which we know the cost-min path from start
		Set<Node<N>> finished = new HashSet<Node<N>>();
		
		// Add a path from start to itself in active. This is the shortest path with cost of 0.0
		ArrayList<Edge<Double,N>> start2start = new ArrayList<Edge<Double,N>>();
		start2start.add(new Edge<Double,N>(start, start, 0.0));
		active.add(start2start);
		
		// Continue looking for path until found or no options
		while(!active.isEmpty()) {
			// minPath is the lowest cost path in active (top of priority queue)
			ArrayList<Edge<Double,N>> minPath = active.remove();
			
			// Destination node in minPath
			Node<N> minDest = minPath.get(minPath.size() - 1).getChild();
			
			if(minDest.equals(dest)) {
				return minPath;
			} 
			
			// haven't gotten to our destination yet
			if(!finished.contains(minDest)) {
				
				// Get all the children from minDest and look at those potential paths
				Set<Edge<Double,N>> destChildren = graph.getChildren(minDest);
				for(Edge<Double,N> edge : destChildren) {
					Node<N> child = edge.getChild();
					
					if(!finished.contains(child)) {
						ArrayList<Edge<Double,N>> newPath = new ArrayList<Edge<Double,N>>(minPath);
						newPath.add(edge);
						active.add(newPath);
					}
				}
				// Add minDest to finished
				finished.add(minDest);
			}
		}
		// If loop terminates and no path is found, a blank list is returned
		return new ArrayList<Edge<Double,N>>();
	}
}

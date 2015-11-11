package hw7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import hw5.*;
import hw6.*;

/**
 * 
 * This class can build a graph from a given .tsv file. It can also search for a path
 * from two given names that are contained within the graph. 
 *
 */
public class MarvelPaths2 {
	/**
	 * This class is not an ADT
	 */
	
	private static Graph<String, Double> marvelGraph;
	private static final String FILE_NAME = "src/hw7/data/marvel.tsv";
	
	public static void main(String[] args) {
		// Initialize the graph
		marvelGraph = new Graph<String,Double>();
		buildGraph(marvelGraph, FILE_NAME);
		
		// Get two character names from the user
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the Marvel character search");
        System.out.println("Given any two character names, a connection can be searched for");
        System.out.println("What is your character one name:");
        String char1 = console.nextLine();
        System.out.println("What is your character two name: ");
        String char2 = console.nextLine();
        System.out.println(); 
        
        // Generate the path
		System.out.println(pathToString(marvelGraph, char1, char2));
		console.close();
	}
	
	/**
	 * Searches for a cost-minimum path of a given graph.
	 * @param graph The graph to search for a path within
	 * @param char1 The starting character in the path.
	 * @param char2 The destination character to search for.
	 * @return Returns an ArrayList with edges that are the given path from
	 * 		   char1 to char2. Empty ArrayList is returned if there is no path.
	 */
	public static ArrayList<Edge<Double,String>> findPath(Graph<String,Double> graph,
			String char1, String char2) {
		ArrayList<Edge<Double,String>> costMinPath = 
				DijkstraAlgorithm.findPath(graph, char1, char2, new PathListComparator());
		return costMinPath;
	}

	/**
	 * Builds the given graph with the given file.
	 * @param someGraph The graph to add all the nodes and edges into.
	 * @param fileName Given filename to build a graph from. Must be .tsv format.
	 */
	public static void buildGraph(Graph<String,Double> someGraph, String fileName) {
		//First just create regular string string graph
		Graph<String,String> temp = new Graph<String,String>();
		MarvelPaths.buildGraph(temp, fileName);
		weightedEdges(someGraph, temp);
	}
	
	/**
	 * Takes an already built graph and converts it to a new graph with weighted edges
	 * based on how connected the nodes are in the original graph. More connected means
	 * a lower edge weight and less connected means a higher edge weight.
	 * @param someGraph The graph to build with weighted edges
	 * @param temp The graph with unweighted edges.
	 */
	public static void weightedEdges(Graph<String,Double> someGraph, Graph<String,String> temp) {
		// go through all the nodes
		Set<Node<String>> nodeList = temp.nodeSet();
		for(Node<String> allNodes : nodeList) {
			someGraph.addNode(allNodes);
		}
		
		for(Node<String> current : nodeList) {
			
			// Now get all the children and look for connections to create weighted edges
			Set<Edge<String,String>> children = temp.getChildren(current);
			List<Edge<String,String>> childList = new ArrayList<Edge<String,String>>();
			for(Edge<String,String> edge : children) {
				childList.add(edge);
			}
			children.clear();

			while(!childList.isEmpty()) {
				Edge<String,String> currEdge = childList.remove(0);
				Node<String> child = currEdge.getChild();
				
				Iterator<Edge<String,String>> itr = childList.iterator();
				
				double count = 1.0;
				while(itr.hasNext()) {
					Edge<String,String> matching = itr.next();
					if(matching.getChild().equals(child)) {
						count += 1.0;
						itr.remove();
					}
				}
				
				Double weight = 1/count;
				someGraph.addEdge(new Edge<Double,String>(current, child, weight));
			}
		}
	}
	
	/**
	 * Creates the string format for the path.
	 * 
	 * @param path A list of the path from character1 to character2
	 * @param char1 Starting character
	 * @param char2 Character that was searched for
	 * @return returns a string format of the path
	 */
	public static String pathToString(Graph<String,Double> graph, String char1, String char2) {
		Node<String> charOne = new Node<String>(char1);
		Node<String> charTwo = new Node<String>(char2);
		String result = "";
		Double total = 0.0;
		
		// Case for unknown characters
	    if(!graph.containsNode(charOne) || !graph.containsNode(charTwo)) {
	    	if(!graph.containsNode(charOne)) {
	    		result += "unknown character " + char1;
	    	}
	    	if(!graph.containsNode(charOne) && !graph.containsNode(charTwo)) {
	    		result += System.lineSeparator() + "unknown character " + char2;
	    	} else if(!graph.containsNode(charTwo)) {
	    		result += "unknown character " + char2;
	    	}
	    	return result;
	    } else {
	    	// If these characters exist
			result = "path from " + char1 + " to " + char2 + ":";
			ArrayList<Edge<Double,String>> path = findPath(graph, char1, char2);
			if(path == null) {
				return result + System.lineSeparator() + "no path found";
			} else if(char1.equals(char2)) {
				return result += System.lineSeparator() + String.format("total cost: %.3f", total);
			} else {
				String prev = char1;
				String current;
				
				// Case for if there is no possible path
				if(path.size() == 0) {
					return result += System.lineSeparator() + "no path found";
				}
				
				// Generate string of path
				for(int i = 2; i < path.size(); i++) {
					current = path.get(i).getParent().getData();
					double edge = roundDouble(path.get(i-1).getValue());
					result += System.lineSeparator() + prev + " to " + current + String.format(
							" with weight %.3f", edge);
					prev = current;
					total += edge;
				}
				Double edge = path.get(path.size() - 1).getValue();
				total += edge;
				result += System.lineSeparator() + prev + " to " + char2 + String.format(
						" with weight %.3f", edge);
			}
	    }
		result += System.lineSeparator() + String.format("total cost: %.3f", total);
		return result;
	}
	
	/**
	 * Calculates the total cost of a path with weighted edges.
	 * @param path A path containing weighted edges.
	 * @return The total cost for the given path based on the weighted edges.
	 */
	public static <N extends Comparable<N>> Double totalCost(List<Edge<Double,N>> path) {
		Double cost = 0.0;
		for(Edge<Double,N> edge : path) {
			cost += edge.getValue();
		}
		return cost;
	}
	
	private static Double roundDouble(Double value) {
		return Math.round(value*1000.0)/1000.0;
	}
}

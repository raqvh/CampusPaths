package hw5;

import java.util.*;

/**
 * This class represents a graph which is made up of nodes and edges 
 * which connect these nodes.
 *
 * Specification fields:
 *  @specfield graph : Map // Maps the nodes in the graph to the edges they
 *  						  are contained in.
 *
 * Abstract Invariant:
 *  A graph cannot contain identical nodes. An edge in the graph may only 
 *  only contain one instance of a parent node pointing to a child node.
 *  A edge may 'self-reference', where it starts and ends on the same node.
 */
public class Graph {
	
	// Abstraction Function.
	// abstraction function here
	//
	
	// Representation Invariant:
	// rep invariant here
	//
	
	//Turn checkRep() debugging on/off
	private final boolean debug = false;
	
	private Map<Node, Set<Edge>> graph;
	
	/**
	 * Initializes a new map with no nodes and no edges.
	 * 
	 * @modifies this
	 * @effects initializes graph
	 */
	public Graph() {
		this.graph = new HashMap<Node, Set<Edge>>();
		checkRep();
	}
	
	/**
	 * Checks if the given edge exists within the graph.
	 * 
	 * @requires given value != null
	 * @return Returns true if given edge exists in the graph; false otherwise.
	 * @throws IllegalArgumentException if value == null
	 */
	public boolean containsEdge(Edge value) {
		checkRep();
		
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		}
		Set<Edge> edges = graph.get(value.getParent());
		for(Edge temp : edges) {
			if(temp.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the given edge from the map.
	 * 
	 * @requires given edge must exist within the graph
	 * @modifies this
	 * @effects updates the values of the map by adding the given edge
	 * @returns the removed edge
	 * @throws IllegalArgumentException if value is null or does not exist in the graph
	 */
	public void removeEdge(Edge value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value.getParent()) || 
				!graph.get(value.getParent()).contains(value)) {
			throw new IllegalArgumentException("Edge does not exist in this graph");
		}
		Set<Edge> edges = graph.get(value.getParent());
		edges.remove(value);
	}
	
	/**
	 * Adds the given edge to the graph.
	 * 
	 * @requires The start and end node in the edge must exist in the graph.
	 * @modifies this
	 * @effects adds the given edge to the set of the parent node
	 * @throws IllegalArgumentException if value == null or parent node and
	 * child node do not exist in the graph.
	 */
	public void addEdge(Edge value) {
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value.getParent()) || !graph.containsKey(value.getChild())) {
			throw new IllegalArgumentException("Edge nodes do not exist in this graph");
		}
		graph.get(value.getParent()).add(value);
		checkRep();
	}
	
	/**
	 * Checks if the given node exists within the graph.
	 * 
	 * @requires given value != null
	 * @return Returns true if given node exists in the graph; false otherwise.
	 * @throws IllegalArgumentException if value == null
	 */
	public boolean containsNode(Node value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException();
		}
		for(Node temp : graph.keySet()) {
			if(temp.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the given node from the graph if it exists in the graph.
	 * 
	 * @requires value != null and Node must exist in the graph
	 * @modifies this
	 * @effects removes the node and all the edges that contain the node from the graph
	 * @throws IllegalArgumentException if value is null or not in the graph
	 */
	public void removeNode(Node value){
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value)) {
			throw new IllegalArgumentException("Node does not exist in the graph");
		}
		graph.remove(value);
		for(Set<Edge> edges : graph.values()) {
			//Use an iterator to safely remove any edge containing the node
			Iterator<Edge> itr = edges.iterator();
			while (itr.hasNext()) {
				Edge s = itr.next();
				if(s.getChild().equals(value)) {
					itr.remove();
				}
			}
		}
		checkRep();
	}
	
	/**
	 * Adds the given Node to the graph.
	 * 
	 * @requires value != null
	 * @modifies this
	 * @effects adds the given node to the graph if it does not already exist
	 * @throws IllegalArgumentException if value == null
	 */
	public void addNode(Node value){
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} 
		if(!graph.containsKey(value)) {
			graph.put(value, new HashSet<Edge>());
		}
		checkRep();
	}

	
	/**
	 * Returns a list of all the edges in the graph
	 * 
	 * @returns a list of all the edges in the current graph
	 */
	public List<Edge> edgeList() {
		checkRep();
		List<Edge> result = new ArrayList<Edge>();
		for(Set<Edge> edges : graph.values()) {
			for(Edge value : edges) {
				result.add(value);
			}
		}
		return result;
	}
	
	/**
	 * @returns a set of all the nodes in the current graph
	 */
	public Set<Node> nodeSet() {
		checkRep();
		return graph.keySet();
	}
	
	/**
	 * Returns a list of all the children nodes of the given node.
	 * 
	 * @requires value != null and value must be in the graph
	 * @returns returns a list of all the children for the given node
	 * @throws IllegalArgumentException if value == null or not in the graph
	 */
	public Set<Node> getChildren(Node value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value)) {
			throw new IllegalArgumentException("Node not in graph");
		}
		Set<Node> children = new HashSet<Node>();
		Set<Edge> edges = graph.get(value);
		for(Edge current : edges) {
			children.add(current.getChild());
		}
		return children;
	}
	
	/**
	 * @returns the number of nodes to edges in this graph
	 */
	public int size() {
		return graph.size();
	}
	
	/**
	 * @returns true if the graph is empty (no nodes); false otherwise
	 */
	public boolean isEmpty() {
		return graph.isEmpty();
	}
	
	/**
	 * @modifies this
	 * @effects clears the graph of all nodes and edges
	 */
	public void clear() {
		graph.clear();
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		if(debug) {
			// Check for no duplicate nodes.
			List<Node> nodes = new ArrayList<Node>();
			for(Node value : graph.keySet()) {
				nodes.add(value);
			}
			for(int i = 0; i < nodes.size(); i++) {
				Node value = nodes.remove(i);
				assert(!nodes.contains(value));
			}
			
			// Check for no duplicate edges. (Same parent and child)
			List<Edge> edges = new ArrayList<Edge>();
			for(Set<Edge> set : graph.values()) {
				for(Edge value : set) {
					edges.add(value);
				}
			}
			for(int i = 0; i < edges.size(); i++) {
				Edge value = edges.remove(i);
				assert(!edges.contains(value));
			}
		}
	}
}

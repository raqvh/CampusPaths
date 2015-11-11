package hw5;

import java.util.*;

/**
 * This class represents a graph which is made up of nodes and edges 
 * which connect these nodes.
 *
 * Specification fields:
 *  @specfield graph : Map // Maps the nodes in the graph to the edges they
 *  						  are contained in.
 *  E represents the type of Edge
 *  N represents the type of Node
 *
 * Abstract Invariant:
 *  A graph cannot contain identical nodes. An edge in the graph may only 
 *  only contain one instance of a parent node pointing to a child node.
 *  A edge may 'self-reference', where it starts and ends on the same node.
 */
public class Graph<N extends Comparable<N>, E extends Comparable<E>> {
	
	// Abstraction Function.
	// Each node of the graph, n, is mapped to a corresponding set of edges where 
	// each element in the set contains an edge with a 'parent' node of that node
	// and a 'child' node.

	// Representation Invariant:
	// for all nodes, n, in the graph, n != null &&
	// for each corresponding set to a node, for all, e, in the
	// given edge set, e != null && e != e.
	
	//Turn checkRep() debugging on/off
	private final boolean debug = false;
	
	private Map<Node<N>, Set<Edge<E,N>>> graph;
	
	/**
	 * Initializes a new graph with no nodes and no edges.
	 * 
	 * @modifies this
	 * @effects initializes graph
	 */
	public Graph() {
		this.graph = new HashMap<Node<N>, Set<Edge<E,N>>>();
		checkRep();
	}
	
	/**
	 * Checks if the given edge exists within the graph.
	 * 
	 * @param Edge the Edge to check for
	 * @requires given value != null
	 * @return Returns true if given edge exists in the graph; false otherwise.
	 * @throws IllegalArgumentException if value == null
	 */
	public boolean containsEdge(Edge<E,N> value) {
		checkRep();
		
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		}
		Set<Edge<E,N>> edges = graph.get(value.getParent());
		for(Edge<E,N> temp : edges) {
			if(temp.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the given edge from the map.
	 * 
	 * @param Edge	Edge to be removed from the graph.
	 * @requires given edge must exist within the graph
	 * @modifies this
	 * @effects updates the values of the map by adding the given edge
	 * @returns the removed edge
	 * @throws IllegalArgumentException if value is null or does not exist in the graph
	 */
	public void removeEdge(Edge<E,N> value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} 
		if(graph.containsKey(value.getParent()) && 
				graph.get(value.getParent()).contains(value)) {
			Set<Edge<E,N>> edges = graph.get(value.getParent());
			edges.remove(value);
		}
	}
	
	/**
	 * Adds the given edge to the graph.
	 * 
	 * @param Edge	the Edge to add to the graph.
	 * @requires The start and end node in the edge must exist in the graph.
	 * @modifies this
	 * @effects adds the given edge to the set of the parent node
	 * @throws IllegalArgumentException if value == null or parent node and
	 * child node do not exist in the graph.
	 */
	public void addEdge(Edge<E,N> value) {
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
	 * @param Node	the Node to check for in the graph.
	 * @requires given value != null
	 * @return Returns true if given node exists in the graph; false otherwise.
	 * @throws IllegalArgumentException if value == null
	 */
	public boolean containsNode(Node<N> value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException();
		}
		for(Node<N> temp : graph.keySet()) {
			if(temp.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the given node from the graph if it exists in the graph.
	 * 
	 * @param Node	the Node to remove from the graph.
	 * @requires value != null and Node must exist in the graph
	 * @modifies this
	 * @effects removes the node and all the edges that contain the node from the graph
	 * @throws IllegalArgumentException if value is null or not in the graph
	 */
	public void removeNode(Node<N> value){
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value)) {
			throw new IllegalArgumentException("Node does not exist in the graph");
		}
		graph.remove(value);
		for(Set<Edge<E,N>> edges : graph.values()) {
			//Use an iterator to safely remove any edge containing the node
			Iterator<Edge<E,N>> itr = edges.iterator();
			while (itr.hasNext()) {
				Edge<E,N> s = itr.next();
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
	 * @param Node	the Node to add to the graph.
	 * @requires value != null
	 * @modifies this
	 * @effects adds the given node to the graph if it does not already exist
	 * @throws IllegalArgumentException if value == null
	 */
	public void addNode(Node<N> value){
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} 
		if(!graph.containsKey(value)) {
			graph.put(value, new TreeSet<Edge<E,N>>());
		}
		checkRep();
	}

	
	/**
	 * Returns a list of all the edges in the graph
	 * 
	 * @returns a list of all the edges in the current graph
	 */
	public List<Edge<E,N>> edgeList() {
		checkRep();
		List<Edge<E,N>> result = new ArrayList<Edge<E,N>>();
		for(Set<Edge<E,N>> edges : graph.values()) {
			for(Edge<E,N> value : edges) {
				result.add(value);
			}
		}
		return result;
	}
	
	/**
	 * Returns a set of all nodes in the graph.
	 * 
	 * @returns a set of all the nodes in the current graph
	 */
	public Set<Node<N>> nodeSet() {
		checkRep();
		return graph.keySet();
	}
	
	
	/**
	 * Returns a list of all the children nodes of the given node.
	 * 
	 * @param Node	the Node to get the children nodes for.
	 * @requires value != null and value must be in the graph
	 * @returns returns a list of all the children for the given node
	 * @throws IllegalArgumentException if value == null or not in the graph
	 */
	/*
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
	*/
	public Set<Edge<E,N>> getChildren(Node<N> value) {
		checkRep();
		if(value == null) {
			throw new IllegalArgumentException("Invalid parameter");
		} else if(!graph.containsKey(value)) {
			throw new IllegalArgumentException("Node not in graph");
		}
		return graph.get(value);
	}
	
	/**
	 * The current number of nodes to edge mappings in the graph.
	 * 
	 * @returns the number of nodes to edges in this graph
	 */
	public int size() {
		return graph.size();
	}
	
	/**
	 * Returns if the graph is empty or not.
	 * 
	 * @returns true if the graph is empty (no nodes); false otherwise
	 */
	public boolean isEmpty() {
		return graph.isEmpty();
	}
	
	/**
	 * Clears the graph.
	 * 
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
			List<Node<N>> nodes = new ArrayList<Node<N>>();
			for(Node<N> value : graph.keySet()) {
				nodes.add(value);
			}
			for(int i = 0; i < nodes.size(); i++) {
				Node<N> value = nodes.remove(i);
				assert(!nodes.contains(value));
			}
			
			// Check for no duplicate edges. (Same parent and child)
			List<Edge<E,N>> edges = new ArrayList<Edge<E,N>>();
			for(Set<Edge<E,N>> set : graph.values()) {
				for(Edge<E,N> value : set) {
					edges.add(value);
				}
			}
			for(int i = 0; i < edges.size(); i++) {
				Edge<E,N> value = edges.remove(i);
				assert(!edges.contains(value));
			}
		}
	}
}

package hw5;

/**
 * This class represents a mutable singular node as part of a graph. A node can hold
 * some form of data.
 *
 * Specification fields:
 *  @specfield data : String // The information that the node represents.
 *
 *
 * Abstract Invariant:
 *  A node must not be null.
 */
public class Node implements Comparable<Node> {
	
	private String data;

	// Abstraction Function:
	// Some abstraction function of what a node is..
	// blah blah blah
	
	// Representation invariant for every Node n:
	// rep invariaent for every node
	// Node value != null

	/**
	 * Creates a new node with an empty string.
	 * 
	 * @modifies this
	 * @effects Sets data equal to a blank string. (data = "")
	 */
	public Node() {
		this("");
	}
	
	/**
	 * Creates a new node with the given value.
	 * 
	 * @requires value != null
	 * @modifies this
	 * @effects Sets data equal to the given value.
	 * @throws IllegalArgumentException if value == null.
	 */
	public Node(String value) {
		this.data = value;
		checkRep();
	}
	
	/**
	 * Gets the current value of this node.
	 * 
	 * @returns The current value this represents.
	 */
	public String getData() {
		return this.data;
	}
	
	/**
	 * Returns true if Node stores an empty value.
	 * 
	 * @returns True if data is a blank string, otherwise false.
	 */
	public boolean isEmpty() {
		checkRep();
		return (this.data.equals(""));
	}
	
	//toString();
	/**
	 * Compares this to another object of type Node.
	 * 
	 * @returns If this > other returns a positive int.
	 * 		 If this < other returns a negative int.
	 * 		 If this == other returns 0.
	 */
	public int compareTo(Node other) {
		checkRep();
		return this.data.compareTo(other.data);
	}
	
	/**
	 * Compares if this equals other object of Node.
	 * 
	 * @requires o is an instanceof Node
	 * @returns True if o equals this; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Node))
			return false;
		Node other = (Node)o;
		return other.data.equals(this.data);
	}
	
	/**
	 * @returns a hash code for this
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 * this.data.hashCode();
		return result;
	}
	
	/**
	 * @returns a string representation of this
	 */
	@Override
	public String toString() {
		return "Node: " + this.data;
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		assert (this.data != null);
	}
}

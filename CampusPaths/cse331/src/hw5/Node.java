package hw5;

/**
 * This class represents a mutable singular node as part of a graph. A node can hold
 * some form of data.
 *
 * Specification fields:
 *  @specfield data : String // The information that the node represents.
 *
 * E represents the type of data stored in the node.
 *
 * Abstract Invariant:
 *  A node must not be null.
 */
public class Node<E extends Comparable<E>> implements Comparable<Node<E>> {	
	private E data;

	// Abstraction Function:
	// Each node represents some string data
	
	// Representation invariant for every Node n:
	// n.value != null
	
	/**
	 * Creates a new node with the given value.
	 * 
	 * @param E	the value this Node represents
	 * @requires value != null
	 * @modifies this
	 * @effects Sets data equal to the given value.
	 * @throws IllegalArgumentException if value == null.
	 */
	public Node(E value) {
		this.data = value;
		checkRep();
	}
	
	/**
	 * Gets the current value of this node.
	 * 
	 * @returns The current value this represents.
	 */
	public E getData() {
		return this.data;
	}
	
	/**
	 * Compares this to another object of type Node.
	 * 
	 * @param Node	the object to compare 'this' to
	 * @returns If this > other returns a positive int.
	 * 		 If this < other returns a negative int.
	 * 		 If this == other returns 0.
	 */
	public int compareTo(Node<E> other) {
		checkRep();
		return this.data.compareTo(other.data);
	}
	
	/**
	 * Compares if this equals other object of the same Node type.
	 * 
	 * @param Object	the Object to compare 'this' to
	 * @requires o is an instanceof Node
	 * @returns True if o equals this; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Node<?>))
			return false;
		Node<?> other = (Node<?>)o;
		return this.data.equals(other.data);
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
		return "Node: " + this.data.toString();
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		assert (this.data != null);
	}
}

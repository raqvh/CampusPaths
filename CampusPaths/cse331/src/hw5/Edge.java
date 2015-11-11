package hw5;

/**
 * This class represents an immutable edge as part of a graph. 
 * An edge connects two node objects. It starts at one node (parent)
 * and 'points' to another node (child), which is the direction. 
 * An edge can also store some data.
 * 
 * Edge:
 * 		'start'            'end'	
 * 		[parent] -------> [child]
 *
 * Specification fields:
 *  @specfield start : Node // The starting node of the edge. (Parent Node)
 *  @specfield end   : Node // The ending node of the edge. (Child Node)
 *  @specfield data  : String // The information that the edge represents.
 *  
 *  E represents the data type of Edge
 *  N represents the data type of Node in Edge
 *
 *
 * Abstract Invariant:
 *  The starting and ending nodes must be valid. The value stored != null.
 */
public class Edge<E extends Comparable<E>, N extends Comparable<N>> implements Comparable<Edge<E, N>> {
	private final Node<N> start;
	private final Node<N> end;
	private final E data;
		
	// Abstraction Function:
	// Each edge represents a 'parent' node directing to a 'child' node.
	// Edge: 'Parent Node' ----> 'Child Node'
	
	// Representation Invariant for every Edge e:
	// e.start != null && e.end != null && e.data != null
	
	/**
	 * Constructs a new edge with the given starting and ending nodes
	 * and stores the given value representing the edge.
	 * 
	 * @param Node	the 'parent' Node for this edge
	 * @param Node	the 'child' Node this edge is directing to
	 * @param String	the value this edge represents
	 * @requires start != null, end != null, & value != null
	 * @modifies this
	 * @effects Sets start to the given start node. Sets end to the given end
	 * 		    node. Sets data to the given value.
	 * @throws IllegalArgumentException if start, end, or value == null.
	 */
	public Edge(Node<N> start, Node<N> end, E value) {
		if(start == null || end == null || value == null) {
			throw new IllegalArgumentException("Invalid parameters");
		}
		this.start = start;
		this.end = end;
		this.data = value;
	}
	
	/**
	 * Returns the starting node of this.
	 * 
	 * @returns The starting node for the edge.
	 */
	public Node<N> getParent() {
		checkRep();
		return this.start;
	}
	
	/**
	 * Returns the ending (destination) node of this.
	 * 
	 * @returns The ending node for the edge.
	 */
	public Node<N> getChild() {
		checkRep();
		return this.end;
	}
	
	/**
	 * Returns the value this edge represents. 
	 * 
	 * @returns what it returns
	 */
	public E getValue() {
		checkRep();
		return this.data;
	}
	
	
	/**
	 * Compares if this equals other object of type Edge.
	 * 
	 * @param Object	the object to compare 'this' to
	 * @requires o is an instance of Edge
	 * @returns True if o equals this; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Edge<?,?>))
			return false;
		Edge<?,?> other = (Edge<?,?>)o;
		if(!this.start.equals(other.start)) {
			return false;
		}
		else if(!this.end.equals(other.end)) {
			return false;
		} else {
			return other.data.equals(this.data);
		}
	}
	
	/**
	 * @returns a hash code for this
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 * this.data.hashCode();
		result = result * 31 * this.start.hashCode();
		result = result * 31 * this.end.hashCode();
		return result;
	}
	
	/**
	 * @returns a string representation of this
	 */
	@Override
	public String toString() {
		String result = "Edge: " + this.data.toString() + "[";
		result += this.start.toString() + ", ";
		result += this.end.toString();
		return result + "]";
	}
	
	/** 
	 * Checks that the representation invariant holds (if any).
	 */
	private void checkRep() {
		assert (data != null);
		assert (start != null);
		assert (end != null);
	}

	@Override
	public int compareTo(Edge<E, N> o) {
		if(this.start.compareTo(o.start) != 0) {
			return this.start.compareTo(o.start);
		} else if(this.end.compareTo(o.end) != 0) {
			return this.end.compareTo(o.end);
		} else {
			return this.data.compareTo(o.data);
		}
	}

}

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
 *
 * Abstract Invariant:
 *  The starting and ending nodes must be valid. The value stored != null.
 */
public class Edge {
	private final Node start;
	private final Node end;
	private final String data;
		
	// Abstraction Function:
	// Some abstraction function here...
	
	// Representation Invariant:
	// The rep invariant here for an edge...
	
	/**
	 * Constructs a new edge with the given starting and ending nodes
	 * and stores the given value representing the edge.
	 * 
	 * @requires start != null, end != null, & value != null
	 * @modifies this
	 * @effects Sets start to the given start node. Sets end to the given end
	 * 		    node. Sets data to the given string value.
	 * @throws IllegalArgumentException if start, end, or value == null.
	 */
	public Edge(Node start, Node end, String value) {
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
	public Node getParent() {
		checkRep();
		return this.start;
	}
	
	/**
	 * Returns the ending (destination) node of this.
	 * 
	 * @returns The ending node for the edge.
	 */
	public Node getChild() {
		checkRep();
		return this.end;
	}
	
	/**
	 * Returns the value this edge represents. 
	 * 
	 * @returns what it returns
	 */
	public String getValue() {
		checkRep();
		return this.data;
	}
	
	
	/**
	 * Compares if this equals other object of type Edge.
	 * 
	 * @requires o is an instance of Edge
	 * @returns True if o equals this; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Edge))
			return false;
		Edge other = (Edge)o;
		if(this.start != other.start) {
			return false;
		}
		else if(this.end != other.end) {
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
		String result = "Edge: " + this.data + "[";
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

}

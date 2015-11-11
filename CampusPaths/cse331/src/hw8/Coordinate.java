package hw8;

/**
 * This class represents a location in (x,y) coordinate space. Location 
 * is kept as a double
 * 
 * Specification fields:
 * 	@specfield x : Double // The X location of this coordinate
 *  @specfield y : Double // The Y location of this coordinate
 *
 *Abstract Invariant: 
 * for a coordinate (x,y) x != null & y != null
 */
public class Coordinate implements Comparable<Coordinate> {
	// Abstraction Function:
	//   Each coordinate represents some Cartesian (x,y) location.
	//   Where x represents the x coordinate and y represents the y coordinate.
	
	// Representation invariant for every Coordinate c:
	//   c.x != null
	//   c.y != null
	
	private final Double x;
	private final Double y;
	
	/**
	 * Creates a new coordinate at the origin.
	 * Origin is declared to be (0,0).
	 * @modifies this
	 * @effects x and y are set to 0.0 (the origin)
	 */
	public Coordinate() {
		this(0.0,0.0);
	}
	
	/**
	 * Creates a new coordinate with the given values.
	 * 
	 * @param x - the x coordinate for this location
	 * @param y - the y coordinate for this location
	 * @requires the given (x,y) data must be valid numbers.
	 * @modifies this
	 * @effects x coordinate is set to the given x value and 
	 *          y coordinate is set to the given y value
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	/**
	 * @return the value of x for this coordinate
	 */
	public double getX() {
		return new Double(x);
	}

	/**
	 * @return the value of y for this coordinate
	 */
	public double getY() {
		return new Double(y);
	}
	
	/**
	 * @return a string representation of this coordinate
	 *         in the format (x, y) where x and y are the
	 *         values for this coordinate.
	 */
	@Override
	public String toString() {
		return String.format("%.0f", x) + ", " + 
				String.format("%.0f",y);
	}
	
	/**
	 * Compares if this equals other object of the same Coordinate type.
	 * 
	 * @param Object	the Object to compare 'this' to
	 * @requires o is an instanceof Coordinate
	 * @returns True if o equals this; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if(o == this) 
			return true;
		if(!(o instanceof Coordinate)) 
			return false;
		Coordinate other = (Coordinate)o;
		return (this.x.equals(other.x) && this.y.equals(other.y));
	}
	
	/**
	 * @returns a hash code for this
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = (int) (result * 31 * this.x);
		result = (int) (result * 31 * this.y);
		return result;
	}

	/**
	 * @param Coordinate the coordinate to compare the distance of
	 *        'this' to.
	 * @return the distance between the coordinates of 'this' and 'other'
	 */
	public double distance(Coordinate other) {
		double dx = this.x - other.x;
		double dy = this.y - other.y;
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	/**
	 * Compares this to another object of type Coordinate.
	 * 
	 * @param Coordinate	the object to compare 'this' to
	 * @returns If this > other returns a positive int.
	 * 		 If this < other returns a negative int.
	 * 		 If this == other returns 0.
	 */
	public int compareTo(Coordinate other) {
		Coordinate origin = new Coordinate();
		double d = distance(origin) - other.distance(origin);
		if(d > 0.0) {
			return 1;
		} else if(d < 0.0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Checks that the representation invariant holds (if any).
	 */
	public void checkRep() {
		assert(x != null);
		assert(y != null);
	}
}

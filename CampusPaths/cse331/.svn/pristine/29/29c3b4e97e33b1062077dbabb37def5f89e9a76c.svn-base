package hw7;

import hw5.Edge;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * This class is used to create a comparator for ArrayList of weighted edges.
 *
 */
public class PathListComparator implements
	Comparator<ArrayList<Edge<Double,String>>> {
	/**
	 * This class is not an ADT
	 */

	/**
	 * Compares two ArrayLists containing weighted Edges based on their total cost.
	 */
	@Override
	public int compare(ArrayList<Edge<Double, String>> o1,
			ArrayList<Edge<Double, String>> o2) {

		// First compare by cost
		Double cost1 = MarvelPaths2.totalCost(o1);
		Double cost2 = MarvelPaths2.totalCost(o2);
		return cost1.compareTo(cost2);

	}
}

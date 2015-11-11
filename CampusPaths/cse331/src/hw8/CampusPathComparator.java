package hw8;

import hw5.Edge;
import hw7.MarvelPaths2;

import java.util.ArrayList;
import java.util.Comparator;

public class CampusPathComparator implements Comparator<ArrayList<Edge<Double,Coordinate>>> {
	/**
	 * This class is not an ADT
	 */

	/**
	 * Compares two ArrayLists containing weighted Edges based on their total cost.
	 */
	@Override
	public int compare(ArrayList<Edge<Double, Coordinate>> o1,
			ArrayList<Edge<Double, Coordinate>> o2) {

		// First compare by cost
		Double cost1 = MarvelPaths2.totalCost(o1);
		Double cost2 = MarvelPaths2.totalCost(o2);
		return cost1.compareTo(cost2);

	}
}

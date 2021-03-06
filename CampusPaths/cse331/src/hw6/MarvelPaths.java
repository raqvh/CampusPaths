package hw6;

import java.util.*;

import hw5.*;
import hw6.MarvelParser.MalformedDataException;

/**
 * 
 * This client program is for the use of searching for a 'path' between
 * two characters to see how they are connected. Paths are found via BFS
 * search. 
 *
 */
public class MarvelPaths {
	/**
	 * This class is not an ADT
	 */
	private static Graph<String,String> marvelGraph;
	private static final String FILE_NAME = "src/hw6/data/marvel.tsv";
	
	public static void main(String[] args) {
		// Initialize the graph
		marvelGraph = new Graph<String,String>();
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
	 * Builds a graph with nodes and edges from the given filename.
	 * 
	 * @param someGraph A graph to be added to
	 * @param fileName the file containing the information in (.tsv) format to be 
	 * added to the graph
	 */
	public static void buildGraph(Graph<String,String> someGraph, String fileName) {
		Set<String> characters = new HashSet<String>();
		Map<String, List<String>> books = new HashMap<String, List<String>>();
		
		try {
			MarvelParser.parseData(fileName, characters, books);
		} catch (MalformedDataException e) {
			e.printStackTrace();
		}
		
		// add all the characters as nodes
		Iterator<String> itr = characters.iterator();
		while(itr.hasNext()) {
			String character = itr.next();
			if(character != null) {
				Node<String> value = new Node<String>(character);
				someGraph.addNode(value);
			}
		}
		
		// add all the edges
		for(String book : books.keySet()) {
			List<String> charList = books.get(book);
			for(int i = 0; i < charList.size() - 1; i++) {
				for(int j= i+1; j < charList.size(); j++) {
					someGraph.addEdge(new Edge<String,String>(new Node<String>(charList.get(i)), 
							new Node<String>(charList.get(j)), book));
					someGraph.addEdge(new Edge<String,String>(new Node<String>(charList.get(j)),
						new Node<String>(charList.get(i)), book));
				}
			}
		}
	}
	
	/**
	 * Takes the names of two characters and searches for the shortest path
	 * between the two using BFS search.
	 * 
	 * @param temp the Graph to search for a path within
	 * @param char1 the starting character
	 * @param char2 the character to search for
	 * @return returns a string format of the path of character1 to character2
	 */
	public static List<Edge<String,String>> findPath(Graph<String,String> temp, String char1, String char2) {
	    Node<String> firstCharacter = new Node<String>(char1);
	    Node<String> endCharacter = new Node<String>(char2);
	    Queue<Node<String>> worklist = new LinkedList<Node<String>>();
	    Map<Node<String>, List<Edge<String,String>>> nodeToPath = 
	    		new HashMap<Node<String>, List<Edge<String,String>>>();
	    
	    worklist.add(firstCharacter);
	    nodeToPath.put(firstCharacter, new ArrayList<Edge<String,String>>());
	    	   
	    // Uses BFS algorithm here
	    while(!worklist.isEmpty()) {
	    	Node<String> current = worklist.remove();
	    	if(current.equals(endCharacter)) {
	    		return nodeToPath.get(current);
	    	}
	    	for(Edge<String,String> value : temp.getChildren(current)) { 
	    		Node<String> valueChild = value.getChild(); 
	    	    if(!nodeToPath.containsKey(valueChild)) { 
	    	    	List<Edge<String,String>> newPath = 
	    	    			new ArrayList<Edge<String,String>>(nodeToPath.get(current)); 
	    	    	newPath.add(value);
	    	    	nodeToPath.put(valueChild, newPath);
	    	    	worklist.add(valueChild);
	    	    }
	    	}

	    }
	    //return pathToString(null, char1, char2);
	    return null;
	}
	
	/**
	 * Creates the string format for the path.
	 * 
	 * @param path A list of the path from character1 to character2
	 * @param char1 Starting character
	 * @param char2 Character that was searched for
	 * @return returns a string format of the path
	 */
	public static String pathToString(Graph<String,String> graph, String char1, String char2) {
		Node<String> charOne = new Node<String>(char1);
		Node<String> charTwo = new Node<String>(char2);
		String result = "";
		
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
			List<Edge<String,String>> path = findPath(graph, char1, char2);
			if(path == null) {
				return result + System.lineSeparator() + "no path found";
			} else if(char1.equals(char2)) {
				return result + System.lineSeparator();// + "Same character";
			} else {
				String prev = char1;
				String current;
				for(int i = 1; i < path.size(); i++) {
					current = path.get(i).getParent().getData();
					result += System.lineSeparator() + prev + " to " + current + " via "
							+ path.get(i-1).getValue();
					prev = current;
				}
				String edge = path.get(path.size() - 1).getValue().toString();
				result += System.lineSeparator() + prev + " to " + char2 + " via " + edge;
			}
	    }
		return result;
	}
}

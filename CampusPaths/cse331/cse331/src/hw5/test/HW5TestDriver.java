package hw5.test;

import java.io.*;
import java.util.*;
import hw5.*;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class HW5TestDriver {

    public static void main(String args[]) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            HW5TestDriver td;

            if (args.length == 0) {
                td = new HW5TestDriver(new InputStreamReader(System.in),
                                       new OutputStreamWriter(System.out));
            } else {

                String fileName = args[0];
                File tests = new File (fileName);

                if (tests.exists() || tests.canRead()) {
                    td = new HW5TestDriver(new FileReader(tests),
                                           new OutputStreamWriter(System.out));
                } else {
                    System.err.println("Cannot read from " + tests.toString());
                    printUsage();
                    return;
                }
            }

            td.runTests();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("to read from a file: java hw5.test.HW5TestDriver <name of input script>");
        System.err.println("to read from standard in: java hw5.test.HW5TestDriver");
    }

    /** String -> Graph: maps the names of graphs to the actual graph **/
    private final Map<String, Graph> graphs = new HashMap<String, Graph>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @requires r != null && w != null
     *
     * @effects Creates a new HW5TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     **/
    public HW5TestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @effects Executes the commands read from the input and writes results to the output
     * @throws IOException if the input or output sources encounter an IOException
     **/
    public void runTests()
        throws IOException
    {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            }
            else
            {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if (command.equals("RemoveNode")) {
            	removeNode(arguments);
            } else if (command.equals("RemoveEdge")) {
            	removeEdge(arguments);
            } else if (command.equals("ContainsNode")) {
            	containsNode(arguments);
            } else if (command.equals("ContainsEdge")) {
            	containsEdge(arguments);
            } else if (command.equals("IsEmpty")) {
            	isEmpty(arguments);
            } else if (command.equals("Clear")) {
            	clear(arguments);
            } else if (command.equals("Size")) {
            	size(arguments);
            } else {
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }

    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        graphs.put(graphName, new Graph());
        output.println("Created graph " +graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        // Insert your code here.
    	Graph g = graphs.get(graphName);
    	g.addNode(new Node(nodeName));
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
            String edgeLabel) {
        // Insert your code here.
    	Graph g = graphs.get(graphName);
    	g.addEdge(new Edge(new Node(parentName), new Node(childName), edgeLabel));
        output.println("added edge " + edgeLabel + " from " + parentName + 
        				" to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        Graph g = graphs.get(graphName);
        String nodes = "";
        for(Node temp : g.nodeSet()) {
        	nodes += " " + temp.getData();
        }
        output.println(graphName + " contains:" + nodes);
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        Graph g = graphs.get(graphName);
        Set<Node> children = g.getChildren(new Node(parentName));
        String allChildren = "";
        for(Node child : children) {
        	allChildren += " " + child.getData();
        }
        output.println("the children of " + parentName + " in " + graphName + 
        		" are:" + allChildren);
    }
    
    private void removeNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to removeNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        removeNode(graphName, nodeName);
    }

    private void removeNode(String graphName, String nodeName) {
    	Graph g = graphs.get(graphName);
    	g.removeNode(new Node(nodeName));
        output.println("removed node " + nodeName + " from " + graphName);
    }
    
    private void containsNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to containsNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        containsNode(graphName, nodeName);
    }

    private void containsNode(String graphName, String nodeName) {
    	Graph g = graphs.get(graphName);
    	g.containsNode(new Node(nodeName));
        output.println(graphName + " contains " + nodeName + " is: "
        		+ g.containsNode(new Node(nodeName)));
    }
    
    private void containsEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to containsEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        containsEdge(graphName, parentName, childName, edgeLabel);
    }

    private void containsEdge(String graphName, String edgeLabel, String parentName, 
    		String childName) {
    	Graph g = graphs.get(graphName);
    	Edge contained = new Edge(new Node(parentName), new Node(childName), edgeLabel);
        output.println(graphName + " contains " + edgeLabel + " is: "
        		+ g.containsEdge(contained));
    }
    
    private void removeEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to containsEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        removeEdge(graphName, parentName, childName, edgeLabel);
    }

    private void removeEdge(String graphName, String edgeLabel, String parentName, 
    		String childName) {
    	Graph g = graphs.get(graphName);
    	Edge remove = new Edge(new Node(parentName), new Node(childName), edgeLabel);
    	g.removeEdge(remove);
        output.println("removed edge " + edgeLabel + " from " + graphName);
    }

    private void clear(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        clear(graphName);
    }

    private void clear(String graphName) {
        Graph g = graphs.get(graphName);
        g.clear();
        output.println(graphName + "was cleared");
    }
    
    private void isEmpty(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        isEmpty(graphName);
    }

    private void isEmpty(String graphName) {
        Graph g = graphs.get(graphName);
        output.println(graphName + "is empty: " + g.isEmpty());
    }
    
    private void size(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        size(graphName);
    }

    private void size(String graphName) {
        Graph g = graphs.get(graphName);
        output.println("size of " + graphName + " is: " + g.size());
    }
    
    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }
        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}

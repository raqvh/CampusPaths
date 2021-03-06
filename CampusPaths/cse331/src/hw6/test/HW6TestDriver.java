package hw6.test;

import hw5.*;
import hw6.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph<String,String>, the Marvel parser, and your BFS
 * algorithm.
 **/
public class HW6TestDriver {


  public static void main(String args[]) {
      try {
          if (args.length > 1) {
              printUsage();
              return;
          }

          HW6TestDriver td;

          if (args.length == 0) {
              td = new HW6TestDriver(new InputStreamReader(System.in),
                                     new OutputStreamWriter(System.out));
          } else {

              String fileName = args[0];
              File tests = new File (fileName);

              if (tests.exists() || tests.canRead()) {
                  td = new HW6TestDriver(new FileReader(tests),
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
  
  private final Map<String, Graph<String,String>> graphs = new HashMap<String, Graph<String,String>>();
  private final PrintWriter output;
  private final BufferedReader input;
  
  public HW6TestDriver(Reader r, Writer w) {
      input = new BufferedReader(r);
      output = new PrintWriter(w);
  }

  public void runTests() throws IOException {
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
          if (command.equals("LoadGraph")) {
              loadGraph(arguments);
          } else if (command.equals("CreateGraph")) {
              createGraph(arguments);
          } else if (command.equals("AddNode")) {
        	  addNode(arguments);
          } else if (command.equals("AddEdge")) {
        	  addEdge(arguments);
          } else if (command.equals("ListChildren")) {
        	  listChildren(arguments);
          } else if (command.equals("FindPath")) {
        	  findPath(arguments);
          } else if (command.equals("ListNodes")) {
        	  listNodes(arguments);
          } else {
              output.println("Unrecognized command: " + command);
          }
      } catch (Exception e) {
          output.println("Exception: " + e.toString());
      }
  }

	public void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String char1 = arguments.get(1).replace("_", " ");
        String char2 = arguments.get(2).replace("_", " ");
        findPath(graphName, char1, char2);
	}
	
	private void findPath(String graphName, String char1, String char2) {
		output.println(MarvelPaths.pathToString(graphs.get(graphName), char1, char2));
	}
	
	public void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
	}
	
	private void listChildren(String graphName, String char1) {
        Graph<String,String> g = graphs.get(graphName);
        Set<Edge<String,String>> children = g.getChildren(new Node<String>(char1));
        String allChildren = "";
        for(Edge<String,String> child : children) {
        	allChildren += " " + child.getChild().getData() + "(" + child.getValue() + ")";
        }
        output.println("the children of " + char1 + " in " + graphName + 
        		" are:" + allChildren);
	}

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        output.print(graphName + " contains:");
        
        Graph<String,String> g = graphs.get(graphName);

        Set<Node<String>> allNodes = g.nodeSet();
        List<Node<String>> allNodesSort = new ArrayList<Node<String>>();
        
        for(Node<String> temp : allNodes) {
        	allNodesSort.add(temp);
        }
        
        Collections.sort(allNodesSort);
        for(int i = 0; i < allNodesSort.size(); i++) {
        	output.print(" " + allNodesSort.get(i).getData());
        }
        
        output.println();
    }
	
	public void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
	}
	
	private void addEdge(String graphName, String char1, String char2,
			String book) {
    	Graph<String,String> g = graphs.get(graphName);
    	g.addEdge(new Edge<String,String>(new Node<String>(char1), new Node<String>(char2), book));
        output.println("added edge " + book + " from " + char1 + 
        				" to " + char2 + " in " + graphName);
	}
	
	public void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
	}
	
	private void addNode(String graphName, String character) {
    	Graph<String,String> g = graphs.get(graphName);
    	g.addNode(new Node<String>(character));
        output.println("added node " + character + " to " + graphName);
	}
	
	public void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);	
	}
	
	private void createGraph(String graphName) {
        graphs.put(graphName, new Graph<String,String>());
        output.println("created graph " + graphName);
	}
	
	public void loadGraph(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);
        loadGraph(graphName, fileName);	
	}
	
	private void loadGraph(String graphName, String fileName) {
		Graph<String,String> current = graphs.get(graphName);
		current = new Graph<String,String>();
		MarvelPaths.buildGraph(current, "src/hw6/data/" + fileName);
		graphs.put(graphName, current);
		output.println("loaded graph " + graphName);
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

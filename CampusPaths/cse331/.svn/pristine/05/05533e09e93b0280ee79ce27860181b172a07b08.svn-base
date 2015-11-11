package hw8;

/**
 * 
 * This class loads the model and view and directs input/output as needed.
 * Basically the core for running the model and view for UW Campus Paths.
 *
 */
public class CampusDataController {

	private static CampusView view;
	private static CampusModel model;
	
    /**
     * Starts the data and view, then continues getting user input until the user
     * specifies to quit.
     * @param args to be used in main
     */
	public static void main(String[] args) {
		
		// Initialize our view and model so we can actually use them.
		view = new CampusView();
		model = new CampusModel();
		
		// Build all the data from campus_paths.dat and campus_buildings.dat
		model.buildData();
		
		// Open scanner and print the menu first
		view.open();
		view.menu();
		
		// Continues getting user input until the user 'quits'
		boolean finished = false;
		while(!finished) {
			view.print("");
			view.promptUser("Enter an option ('m' to see the menu): ");
			String command = view.getCommand();
			
			// If a line is blank or starts with # it is echoed to the output. 
			// Nothing else happens.
			while(command.startsWith("#") || command.length() == 0) {
				view.print(command);
				command = view.getCommand();
			} 
			
			// Handles routing the given command
			if(command.equals("m")) {
				view.menu();
			} else if(command.equals("q")) {
				view.close();
				finished = true;
			} else if(command.equals("r")) {
				getPath();
				//view.blankLine();
			} else if(command.equals("b")) {
				allBuildings();
			} else {
				view.print("Unknown option");
			}
		}
	}
	
	/**
	 *  Gets all the buildings names from the model
	 */
	public static void allBuildings() {
		view.print(model.listBuildings());
	}
	
	/**
	 * 	Gets a path from the given building abbreviations
	 */
	public static void getPath() {
		// get input (Start and end locations)
		view.promptUser("Abbreviated name of starting building: ");
		String start = view.getInput();
		view.promptUser("Abbreviated name of ending building: ");
		String end = view.getInput();
		// print output
		view.print(model.findPath(start,end));
	}
}

package hw8;

import java.util.Scanner;

/**
 * This is the view for UW Campus Paths. 
 * It handles outputting and inputting information for the user.
 */
public class CampusView {
	/*
	 * This class is not an ADT
	 */
	
	private static Scanner userInput;
	
	/**
	 * Prints the menu. Menu has a list of commands with a short description.
	 */
	public void menu() {
		System.out.println("Menu:");
		System.out.println("\tr to find a route");
		System.out.println("\tb to see a list of all buildings");
		System.out.println("\tq to quit");
	}
	
	/**
	 * Gets a command as input from the user.
	 * @return user input
	 */
	public String getCommand() {
		//System.out.println();
		return userInput.nextLine();
	}
	
	/**
	 * Prints the given information to the user.
	 * @param String to print out to the user
	 */
	public void print(String stuffToPrint) {
		System.out.println(stuffToPrint);
	}
	
	/**
	 * Opens the scanner to allow for user input
	 */
	public void open() {
		userInput = new Scanner(System.in);
	}
	
	/**
	 * Closes the scanner. This should be closed after the user chooses 'q'.
	 */
	public void close() {
		userInput.close();
	}
	
	/**
	 * Gets input from the user. (not to be mistaken for getCommand)
	 * @return String input from the user
	 */
	public String getInput() {
		return userInput.nextLine();
	}
	
	/**
	 * Prompts the user with the given string
	 * @param String containing a prompt for the user
	 */
	public void promptUser(String stuffToPrint) {
		System.out.print(stuffToPrint);
	}
}


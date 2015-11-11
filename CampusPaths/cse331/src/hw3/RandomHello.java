package hw3;
import java.util.Random;

/**
 * RandomHello selects a random greeting to display to the user.
 */
public class RandomHello {

	/**
	 * Uses a RandomHello object to print
	 * a random gretting to the console.
	 */
	public static void main(String[] args) {
		RandomHello randomHello = new RandomHello();
		System.out.println(randomHello.getGreeting());
	}
	
	/**
	 * @return a random greeting from a list of five different greetings.
	 */
	public String getGreeting() {
		Random randomGenerator = new Random();
		String[] greetings = new String[5];
		greetings[0] = "Hello, World!";
		greetings[1] = "Sup, dawgs?";
		greetings[2] = "Good morning.";
		greetings[3] = "Afternoon!";
		greetings[4] = "Nice to see you.";
		return greetings[randomGenerator.nextInt(5)];
	}
}

package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;


/**
 * This class is the menu display class for the main and sub menus 
 */
public class AppMenu {
		
	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	private Scanner input;
	
	/**
	 * This displays a border made of "*"
	 */
	private final String X = "*************************************************************************";
	
	
	/**
	 * This constructor initiates the Scanner
	 */
	public AppMenu(){
	
	input = new Scanner(System.in);
		
	}
		
	/**
	 * This method displays the Main menu and prompts user for an input to choose where to go next
	 * 
	 * @return a string in lower case that represents the choice of the player
	 */
	public String showMainMenu() {
		System.out.print("Select one of these options:\n");
		System.out.println("\n\t(P) Play Game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit\n");
		System.out.print("Enter a choice: ");
		
		return input.nextLine().trim().toLowerCase();

	}
	
	/**
	 * This method displays the a sub menu to search for the top players or a specific player 
	 * and prompts user for an input to choose where to go next
	 * 
	 * @return a string in lower case that represents the choice of the player 
	 */
	public String showSearchMenu() {
		System.out.print("Select one of these options:\n");
		System.out.println("\n\t(T) Top Player");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to Main Menu\n");
		System.out.print("Enter a choice: ");

		return input.nextLine().trim().toLowerCase();

	}

	/**
	 * This method displays a question asking for the name of the searched player and prompts user for an input to enter there name
	 * 
	 * @return the name of the user in lower case
	 */
	public String showAskName() {
		System.out.println("Enter a name here:");
		return input.nextLine().trim().toLowerCase();	
		}

	/**
	 * This method displays the top users in descending order
	 * 
	 * 
	 * @param playerList all the player sorted in the amount of wins in descending order 
	 */
	public void showSearchTopLabel() {
		System.out.println("");
		System.out.println("            - TOP PLAYERS -            ");
		System.out.println("+==================+==================+");
		System.out.println("|NAMES             |#WINS             |");
		System.out.println("+==================+==================+");
		
	
		}
	public void showPlayerData(String name, int wins) {
		System.out.println(String.format("|%-18s|%-18s|", name, wins));
		System.out.println("+------------------+------------------+");
		
	}

	
	/**
	 * This method displays an instruction on how to continue and prompts user for input
	 * 
	 * @return an empty string
	 */
	public String enterToContinue(){
		System.out.println("");
		System.out.println("Press " + "\"ENTER\" " + "to continue...");
		return input.nextLine().trim().toLowerCase();	
	}

	/**
	 * This method displays an invalid input message
	 */
	public void showInputErrorMessage() {
		System.out.println("Invalid Input, Try again");
	}

	/**
	 * displays a question that ask what the current players name is
	 * 
	 * @return the current users name as a string in lower case
	 */
	public String showAskUserName() {
		
		System.out.println("What is your name: ");
		return input.nextLine().trim();	
	}


	/**
	 * This method displays a greeting and shows retuning player  balance
	 * 
	 * @param user The  current returning player object
	 */
	public void showWelcomeOldUser(Player user) {
		String name = user.getName().toUpperCase();
		double bal = user.getBalance();
		
		System.out.println(X);
		System.out.println("**   Welcome back " + name + String.format("   ---   Your Balance is: %.2f $", bal) + "\t**");
		System.out.println(X);
	}

	/**
	 * This method displays a greeting and shows the balance of the new player
	 * 
	 * @param user the current new player object that previously did not exist in the database
	 */
	public void showWelcomeNewUser(Player user) {
		//  Shows a message if the person playing this game does not exist in the file
		String name = user.getName().toUpperCase();
		double bal = user.getBalance();
		
		System.out.println(X);
		System.out.println("**   Welcome " + name + String.format("   ---   Your initial Balance is: %.2f $", bal) + "\t**");
		System.out.println(X);
	}

	/**
	 * This method displays the name wins and balance of the player searched
	 * 
	 * @param playerInfo the player object for the player that was searched
	 */
	public void showPlayerInfoLabel() {
		// when the user searches for a player show there stats
		System.out.println("");
		System.out.println("            - PLAYER INFO -            ");
		System.out.println("+==================+==================+");
		System.out.println("|NAMES             |#WINS             |");
		System.out.println("+==================+==================+");
	}

	/**
	 * Displays a message indicating that the player was not found when searching for them
	 */
	public void showPlayerNotFound() {
		// TODO When a player searches for a player and the player does not exists
		System.out.println("player not found");
		
	}
	
	/**
	 * Displays a message indicating tha the saving is in progress and has finished
	 */
	public void showSaveProgress() {
		// SHow a visual to let user know its saving
		System.out.println("");
		System.out.println("Saving...");
		System.out.println("Done! Please visit us again!");
		
	}

	/**
	 * Displays a message indicating that there are no players in the database to compare wins to
	 */
	public void showNoTopPlayers() {
		System.out.println("There are no players yet to add to the leaderboards");
	}
	
	
	
	
}
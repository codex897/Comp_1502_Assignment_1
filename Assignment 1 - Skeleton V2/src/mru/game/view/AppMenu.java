package mru.game.view;

import java.util.Scanner;

import mru.game.model.Player;

public class AppMenu {
		
	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	Scanner input;
	final String X = "***************************************************************************";
	public AppMenu(){
	
	input = new Scanner(System.in);
		
	}
		
		
	public String showMainMenu() {
		System.out.print("Select one of these options:\n");
		System.out.println("\n\t(P) Play Game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit\n");
		System.out.print("Enter a choice: ");
		
		return input.nextLine().trim().toLowerCase();

	}
	
	public String showSearchMenu() {
		System.out.print("Select one of these options:\n");
		System.out.println("\n\t(T) Top Player");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to Main Menu\n");
		System.out.print("Enter a choice: ");

		return input.nextLine().trim().toLowerCase();

	}

	public String showAskName() {
		// TODO Auto-generated method stub
		System.out.println("Enter a name here:");
		System.out.println("Press enter to go back to Main Menu");
		return input.nextLine().trim().toLowerCase();	
		}


	public String showSearchTop() {
		System.out.println("top player is");
		System.out.println("Press " + "enter " + "to continue...");
		return input.nextLine().trim().toLowerCase();		
		}


	public void showInputErrorMessage() {
		System.out.println("Invalid Input, Try again");
		
	}

//////////////////////////////////////////////////////
	public String showAskUserName() {
		// TODO Auto-generated method stub
		System.out.println("What is your name: ");
		return input.nextLine().trim().toLowerCase();	}



	public void showWelcomeOldUser(Player user) {
		// TODO Shows a message if the person playing this game exists
		String name = user.getName().toUpperCase();
		int bal = user.getBalance();
		
		System.out.println(X);
		System.out.println("**\t\tWelcome back " + name + "\t\tYour Balance is $: " + bal + "\t**");
		System.out.println(X);
	}


	public void showWelcomeNewUser(Player user) {
		// TODO  Shows a message if the person playing this game does not exist in the file
		String name = user.getName().toUpperCase();
		int bal = user.getBalance();
		
		System.out.println(X);
		System.out.println("**\t\tWelcome " + name + "\t\tYour initial Balance is $: " + bal + "\t**");
		System.out.println(X);
	}

	public void showPlayerInfo(Player playerInfo) {
		// TODO when the user searches for a player show there stats
		System.out.println("player found");
	}


	public void showPlayerNotFound() {
		// TODO When a player searches for a player and the player does not exists
		System.out.println("player not found");
		
	}
	
	public void showSaveProgress() {
		// TODO SHow a visual to let user know its saving
		System.out.println("Saving...");
		System.out.println("Done! Please visit us again!");
		
	}

//////////////////////////////////////////////////////



}
//
//package mru.game.view;
//
//import java.util.Scanner;
//
//import mru.game.model.Player;
//
//public class AppMenu {
//		
//	/**
//	 * This class will be used to show the menus and sub menus to the user
//	 * It also prompts the user for the inputs and validates them 
//	 */
//	
//	private static final String X = "***************************************************************************";
//	
//	Scanner input;
//	public AppMenu(){
//		input = new Scanner(System.in);
//	}
//		
//		
//	public String showMainMenu() {
//		System.out.print("Select one of these options:\n");
//		System.out.println("\n\t(P) Play Game");
//		System.out.println("\t(S) Search");
//		System.out.println("\t(E) Exit\n");
//		System.out.print("Enter a choice: ");
//		
//		return input.nextLine().trim().toLowerCase();
//
//	}
//	
//	public String showSearchMenu() {
//		System.out.print("Select one of these options:\n");
//		System.out.println("\n\t(T) Top Player");
//		System.out.println("\t(N) Looking for a Name");
//		System.out.println("\t(B) Back to Main Menu\n");
//		System.out.print("Enter a choice: ");
//
//		return input.nextLine().trim().toLowerCase();
//
//	}
//
//	public String showAskName() {
//		// TODO Auto-generated method stub
//		System.out.println("Enter a name here:");
//		System.out.println("Press enter to go back to Main Menu");
//		return input.nextLine().trim().toLowerCase();	
//		}
//
//
//	public String showSearchTop() {
//		System.out.println("top player is");
//		System.out.println("Press " + "enter " + "to continue...");
//		return input.nextLine().trim().toLowerCase();		
//		}
//
//
//	public void showInputErrorMessage() {
//		System.out.println("Invalid Input, Try again");
//		
//	}
//
////////////////////////////////////////////////////////
//	public String showAskUserName() {
//		// TODO Auto-generated method stub
//		System.out.println("What is your name: ");
//		return input.nextLine().trim().toLowerCase();	}
//
//
//	public void showWelcomeOld(Player user) {
//		// TODO Shows a message if the person playing this game exists
//		String name = user.getName().toUpperCase();
//		int bal = user.getBalance();
//		
//		System.out.println(X);
//		System.out.println("**\t\tWelcome back " + name + "\t\tYour Balance is $: " + bal + "\t**");
//		System.out.println(X);
//	}
//
//
//	public void showWelcomeNew(Player user) {
//		// TODO  Shows a message if the person playing this game does not exist in the file
//		String name = user.getName().toUpperCase();
//		int bal = user.getBalance();
//		
//		System.out.println(X);
//		System.out.println("**\t\tWelcome " + name + "\t\tYour initial Balance is $: " + bal + "\t**");
//		System.out.println(X);
//	}
//
//
//	public void showPlayerInfo(Player playerInfo) {
//		// TODO when the user searches for a player show there stats
//		System.out.println("player found");
//	}
//
//
//	public void showPlayerNotFound() {
//		// TODO When a player searches for a player and the player does not exists
//		System.out.println("player not found");
//		
//	}
////////////////////////////////////////////////////////
//
//}

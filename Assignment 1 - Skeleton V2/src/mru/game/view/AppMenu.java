package mru.game.view;

import java.util.Scanner;

public class AppMenu {
		
	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	Scanner input;
	public AppMenu(){
		input = new Scanner(System.in);
	}
		
		
	public String showMainMenu() {
		System.out.println("Select one option:");
		System.out.println("\t1. Play Game");
		System.out.println("\t2. Search");
		System.out.println("\t3. Save and Exit\n");
		System.out.println("Enter a number here: ");
		
		return input.nextLine();

	}
	
	public String showSearchMenu() {
		System.out.println("Select one option:\n");
		System.out.println("\t1. Top Player");
		System.out.println("\t2. Search by Name");
		System.out.println("\t3. Back to Main Menu\n");
		System.out.println("Enter a number here: ");

		return input.nextLine();

	}

	public String showAskName() {
		// TODO Auto-generated method stub
		System.out.println("Enter a name here:");
		System.out.println("Press enter to go back to Main Menu");
		return  input.nextLine();
	}


	public String showSearchTop() {
		System.out.println("top player is");
		System.out.println("Press enter to go back to Main Menu");
		return  input.nextLine();
		
	}


	public void showInputErrorMessage() {
		System.out.println("Invalid Input, Try again");
		
	}


}

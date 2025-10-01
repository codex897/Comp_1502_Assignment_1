package mru.game.view;

import java.util.Scanner;

public class AppMenu {
		
	Scanner input;
		
	public AppMenu(){
		input = new Scanner(System.in);
	}
		
		
	public void showMainMenu() {
		System.out.println("Select one option:");
		System.out.println("\t1. Play Game");
		System.out.println("\t2. Search");
		System.out.println("\t3. Save and Exit\n");
		System.out.println("Enter a number here: ");

	}
	
	public void showSubMenu() {
		System.out.println("Select one option:\n");
		System.out.println("\t1. Top Player");
		System.out.println("\t2. Search by Name");
		System.out.println("\t3. Back to Main Menu\n");
		System.out.println("Enter a number here: ");

		
	}
	
	public void promptName() {
		System.out.println("Enter a name here:");

	}


}
